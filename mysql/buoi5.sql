drop PROCEDURE procedure2
DELIMITER $$
CREATE PROCEDURE procedure2 (in full_name varchar(100), in email varchar(100), in username varchar(100))
begin
declare dep_id int;
declare pos_id int;
select department_id into dep_id
from department where department_name like 'sales';
select position_id into pos_id
from position where position_name like 'Dev';
insert into account (full_name, email, username, department_id, position_id)
values (full_name, email, username, dep_id, pos_id);
end $$
DELIMITER ;
call procedure2('hongphu123', 'hongphu23@gmail.com', 'hongphu27' );
select *from account;

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó 
DROP PROCEDURE IF EXISTS get_account_department;
DELIMITER $$
CREATE PROCEDURE get_account_department(IN p_department_name VARCHAR(50))
BEGIN
    SELECT  acc.account_id,
            acc.full_name,
            acc.email,
            acc.username,
            dep.department_name
    FROM account acc
    INNER JOIN department dep 
        ON acc.department_id = dep.department_id
    WHERE dep.department_name = p_department_name;
END$$
DELIMITER ;
CALL get_account_department('Sales');

-- Question 2: Tạo store để in ra số lượng account trong mỗi group  
DROP PROCEDURE IF EXISTS sp_count_account_in_group;
DELIMITER $$
CREATE PROCEDURE sp_count_account_in_group()
BEGIN
    SELECT 
        g.group_id,
        g.group_name,
        COUNT(ga.account_id) AS so_luong_account
    FROM group_table g
    LEFT JOIN group_account ga ON g.group_id = ga.group_id
    GROUP BY g.group_id, g.group_name;
END$$
DELIMITER ;
CALL sp_count_account_in_group();

-- QUESTION 3: Tạo store thống kê mỗi type question có bao nhiêu question được tạo trong tháng/năm hiện tại
DROP PROCEDURE IF EXISTS sp_count_questions_current_month;
DELIMITER $$
CREATE PROCEDURE sp_count_questions_current_month()
BEGIN
    SELECT 
        tq.type_id,
        tq.type_name,
        COUNT(q.question_id) AS total_questions
    FROM type_question tq
    LEFT JOIN question q ON tq.type_id = q.type_id 
        AND MONTH(q.create_date) = MONTH(CURRENT_DATE()) 
        AND YEAR(q.create_date) = YEAR(CURRENT_DATE())
    GROUP BY tq.type_id, tq.type_name;
END$$
DELIMITER ;
CALL sp_count_questions_current_month;

-- QUESTION 4: Tạo store trả ra ID của type question có nhiều câu hỏi nhất 
DROP PROCEDURE IF EXISTS sp_get_type_id_most_question;
DELIMITER $$
CREATE PROCEDURE sp_get_type_id_most_question(OUT out_type_id TINYINT)
BEGIN
    WITH type_counts AS (
        SELECT type_id, COUNT(question_id) AS total_questions
        FROM question
        GROUP BY type_id
    )
    SELECT type_id INTO out_type_id
    FROM type_counts
    WHERE total_questions = (SELECT MAX(total_questions) FROM type_counts)
    LIMIT 1;
END$$
DELIMITER ;
CALL sp_get_type_id_most_question;
-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question 
DROP PROCEDURE IF EXISTS sp_get_type_name_most_questions;
DELIMITER $$
CREATE PROCEDURE sp_get_type_name_most_questions()
BEGIN
    DECLARE v_type_id TINYINT;
    CALL sp_get_type_id_most_questions(v_type_id);
    SELECT type_id, type_name 
    FROM type_question 
    WHERE type_id = v_type_id;
END$$
DELIMITER ;

-- QUESTION 6: Nhập vào chuỗi -> Tìm group có tên chứa chuỗi HOẶC user có username chứa chuỗi
DROP PROCEDURE IF EXISTS sp_search_group_or_user;
DELIMITER $$
CREATE PROCEDURE sp_search_group_or_user(IN in_search_str VARCHAR(50))
BEGIN
    SELECT group_id AS id, group_name AS name, 'Group' AS type 
    FROM group_table 
    WHERE group_name LIKE CONCAT('%', in_search_str, '%')
    
    UNION ALL
    
    SELECT account_id AS id, user_name AS name, 'User' AS type 
    FROM account_table 
    WHERE user_name LIKE CONCAT('%', in_search_str, '%');
END$$
DELIMITER ;
CALL sp_search_group_or_user;

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào 
DROP PROCEDURE IF EXISTS sp_search_group_or_user;
DELIMITER $$
CREATE PROCEDURE sp_search_group_or_user(IN in_search_string VARCHAR(100))
BEGIN
    SELECT group_id AS id, group_name AS name, 'Group' AS type
    FROM group_table
    WHERE group_name LIKE CONCAT('%', in_search_string, '%')
    UNION ALL
    SELECT account_id AS id, username AS name, 'User' AS type
    FROM account
    WHERE username LIKE CONCAT('%', in_search_string, '%');
END $$
DELIMITER ;
CALL sp_search_group_or_user;
-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:  
			-- username sẽ giống email nhưng bỏ phần @..mail đi  	
			-- positionID: sẽ có default là developer 
			-- departmentID: sẽ được cho vào 1 phòng chờ 
			-- Sau đó in ra kết quả tạo thành công 
DELIMITER $$
CREATE PROCEDURE create_account (IN full_name VARCHAR(100), IN email VARCHAR(100))
BEGIN
    DECLARE v_user_name VARCHAR(100);
    DECLARE v_department_id INT;
    DECLARE v_position_id INT;
    SET v_user_name = SUBSTRING_INDEX(in_email, '@', 1);

    SELECT department_id INTO v_department_id
    FROM department WHERE department_name = 'Chờ việc';

    SELECT position_id INTO v_position_id
    FROM `position` WHERE position_name = 'DEV';

    INSERT INTO `account` (email, user_name, full_name, department_id, position_id)
    VALUES (email, user_name, full_name, department_id, position_id);

    SELECT * FROM `account` ORDER BY account_id DESC LIMIT 1;
END $$
DELIMITER ;
CALL create_account('Hong Phu', 'hongphu123@gmail.com');

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice 
			-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất 
DELIMITER $$
CREATE PROCEDURE get_longest_question (IN in_type_name VARCHAR(30))
BEGIN
	WITH question_by_type AS (
		SELECT q.question_id, q.content, tq.type_name, CHAR_LENGTH(q.content) AS length_content
		FROM question q JOIN type_question tq
		ON q.type_id = tq.type_id
		WHERE tq.type_name = in_type_name
    )
	SELECT * FROM question_by_type
    WHERE length_content = (SELECT MAX(length_content) FROM question_by_type);
END $$
DELIMITER ;
CALL get_longest_question;

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID 
DELIMITER $$
CREATE PROCEDURE delete_exam (IN in_exam_id INT)
BEGIN
    DELETE FROM exam
    WHERE exam_id = in_exam_id;
END $$
DELIMITER ;

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa) 
			-- Sau đó in số lượng record đã remove từ các table liên quan trong khi removing 
DELIMITER $$
CREATE PROCEDURE delete_old_exam()
BEGIN
    DELETE FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id FROM exam
        WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
    );
    DELETE FROM exam
    WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR);
END $$
DELIMITER ;

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập vào tên phòng ban 
			-- và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default là phòng ban chờ việc 
DELIMITER $$
CREATE PROCEDURE delete_department (IN in_department_name VARCHAR(50))
BEGIN
    DECLARE v_department_id INT;
    DECLARE v_default_department_id INT;

    SELECT department_id INTO v_department_id
    FROM department WHERE department_name = in_department_name;

    SELECT department_id INTO v_default_department_id
    FROM department WHERE department_name = 'Chờ việc';

    UPDATE `account`
    SET department_id = v_default_department_id
    WHERE department_id = v_department_id;

    DELETE FROM department
    WHERE department_id = v_department_id;
END $$
DELIMITER ;

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay 
DELIMITER $$
CREATE PROCEDURE get_question_quantity_by_month()
BEGIN
    SELECT 
        MONTH(create_date) AS number_month,
        COUNT(question_id) AS question_quantity
    FROM question
    WHERE YEAR(create_date) = YEAR(CURDATE())
    GROUP BY MONTH(create_date);
END $$
DELIMITER ;

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất  
			-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong  tháng") 
DELIMITER $$
CREATE PROCEDURE get_question_by_6month()
BEGIN
    SELECT
        YEAR(create_date) AS year_number,
        MONTH(create_date) AS month_number,
        COUNT(question_id) AS question_quantity
    FROM question WHERE create_date >= DATE_SUB(CURDATE(), INTERVAL 5 MONTH)
    GROUP BY YEAR(create_date), MONTH(create_date);
END $$
DELIMITER ;