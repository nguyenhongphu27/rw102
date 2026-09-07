-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước 
 DROP TRIGGER IF EXISTS Question1;
DELIMITER $$
CREATE TRIGGER Question1
BEFORE INSERT ON group_table
FOR EACH ROW
BEGIN
    IF NEW.created_date < DATE_SUB(CURDATE(), INTERVAL 1 YEAR) THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'Không được nhập Group có ngày tạo trước 1 năm';
    END IF;
END$$
DELIMITER ;
-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào department "Sale" nữa, khi thêm thì hiện ra thông báo "Department "Sale" cannot add more user" 
DROP TRIGGER IF EXISTS  Question2;
DELIMITER $$
CREATE TRIGGER  Question2
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1
        FROM department
        WHERE department_id = new.department_id
          AND department_name = 'Sale'
    ) THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'không thể thêm người dùng vào bộ phận sales';
    END IF;
END$$
DELIMITER ;
-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user 
DROP TRIGGER IF EXISTS  Question3;
DELIMITER $$
CREATE TRIGGER  Question3
BEFORE INSERT ON groupaccount
FOR EACH ROW
BEGIN
    IF (
        SELECT COUNT(*)
        FROM groupaccount
        WHERE group_id= new.group_id
    ) >= 5 THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'không thể thêm người dùng';
    END IF;
END$$
DELIMITER ;
-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question 
DROP TRIGGER IF EXISTS Question4;
DELIMITER $$
CREATE TRIGGER Question4
BEFORE INSERT ON exam_question
FOR EACH ROW
BEGIN
    IF (
        SELECT COUNT(*)
        FROM exam_question
        WHERE exam_id = new.exam_id
    ) >= 10 THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'tối đa 10 câu hỏi';
    END IF;
END$$
DELIMITER ;
-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là          admin@gmail.com (đây là tài khoản admin, không cho phép user xóa), còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông tin liên quan tới user đó 
DROP TRIGGER IF EXISTS Question5;
DELIMITER $$
CREATE TRIGGER Question5
BEFORE DELETE ON account
FOR EACH ROW
BEGIN
    IF OLD.email = 'admin@gmail.com' THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'không thể xóa tài khoản quản trị viên';
    END IF;
END$$
DELIMITER ;
-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table Account, hãy tạo trigger cho phép người dùng khi tạo account không điền vào departmentID thì sẽ được phân vào phòng ban "waiting Department"   
DROP TRIGGER IF EXISTS Question6;
DELIMITER $$
CREATE TRIGGER Question6
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    DECLARE v_department_id INT;
    IF NEW.department_id IS NULL THEN
        SELECT department_id
        INTO v_department_id
        FROM department
        WHERE department_name = 'bộ phận chờ';
        SET new.department_id = v_department_id;
    END IF;
END$$
DELIMITER ;
-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi question, trong đó có tối đa 2 đáp án đúng. 
DROP TRIGGER IF EXISTS Question7;
DELIMITER $$
CREATE TRIGGER Question7
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
    -- Không quá 4 đáp án cho 1 câu hỏi
    IF (
        SELECT COUNT(*)
        FROM answer
        WHERE question_id = new.question_id
    ) >= 4 THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'một câu hỏi không được có quá 4 câu trả lời';
    END IF;
    -- Không quá 2 đáp án đúng
    IF new.is_correct = 1
       AND (
           SELECT COUNT(*)
           FROM answer
           WHERE question_id = new.question_id
             AND is_correct = 1
       ) >= 2 THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'một câu hỏi không thể có nhiều hơn 2 đáp án đúng';
    END IF;
END$$
DELIMITER ;
-- Question 8: Viết trigger sửa lại dữ liệu cho đúng: Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database 
DROP TRIGGER IF EXISTS Question8;
DELIMITER $$
CREATE TRIGGER Question8
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    IF NEW.gender = 'Nam' THEN
        SET new.gender = 'M';
    ELSEIF new.gender = 'Nữ' THEN
        SET new.gender = 'F';
    ELSEIF new.gender = 'Chưa xác định' THEN
        SET new.gender = 'U';
    END IF;
END$$
DELIMITER ;
-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày 
DROP TRIGGER IF EXISTS Question9;
DELIMITER $$
CREATE TRIGGER Question9
BEFORE DELETE ON exam
FOR EACH ROW
BEGIN
    IF OLD.created_date >= DATE_SUB(NOW(), INTERVAL 2 DAY) THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'không thể xóa bài thi được tạo trong vòng 2 ngày';
    END IF;
END$$
DELIMITER ;
-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các question khi question đó chưa nằm trong exam nào 
-- A: không thể cập nhập câu hỏi vì nó đã có trong bài thi
DROP TRIGGER IF EXISTS Question10;
DELIMITER $$
CREATE TRIGGER Question10
BEFORE UPDATE ON question
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1
        FROM exam_question
        WHERE question_id = OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'không thể cập nhật câu hỏi vì nó đã có trong bài thi';
    END IF;
END$$
DELIMITER ;
-- B: Không thể xóa câu hỏi vì nó đã đã có trong một bài thi
DROP TRIGGER IF EXISTS  Question10;
DELIMITER $$
CREATE TRIGGER Question10
BEFORE DELETE ON question
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1
        FROM exam_question
        WHERE question_id = OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '99999'
        SET MESSAGE_TEXT = 'không thể xóa câu hỏi vì nó đã có trong một bài thi';
    END IF;
END$$
DELIMITER ;