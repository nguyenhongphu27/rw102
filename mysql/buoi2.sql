DROP database IF EXISTS Assignment;
CREATE database Assignment;
use Assignment;
-- table 1 depaertment

DROP TABLE IF EXISTS department;
CREATE TABLE department(
   department_id		INT PRIMARY KEY AUTO_INCREMENT,
   department_name 		VARCHAR(100)
);
INSERT INTO department(department_name)
VALUES
     ('Sales'),
     ('Marketing'),
     ('Human Resources (HR)'),
     ('Information Technology (IT)'),
     ('Finance & Accounting');
     
SELECT * FROM department;

-- table 2 Position 
DROP TABLE IF EXISTS position;
CREATE TABLE `position`(
position_id INT PRIMARY KEY AUTO_INCREMENT,
position_name ENUM ('dev','test','scrum matster', 'pm', 'tu')
);
INSERT INTO `position`(position_name)
VALUES
   ('dev'),
   ('test'),
   ('scrum matster'),
   ('pm'),
   ('tu');
select * from position;
-- table 3 Account
DROP TABLE IF EXISTS account;
CREATE TABLE account(
account_id INT PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(100) UNIQUE,
username VARCHAR(100),
full_name VARCHAR(100),
department_id INT ,
position_id INT,
creat_date DATETIME DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_acc_Dep FOREIGN KEY (Department_id) REFERENCES Department(Department_id),
CONSTRAINT fk_acc_Pos FOREIGN KEY (Position_id) REFERENCES `Position`(Position_id)
);

INSERT INTO `account`(email,username,full_name,department_id,position_id)
VALUES
('nguyenvana@gmail.com','vana','Nguyễn Văn An', 1, 1),
('tranthib@gmail.com' ,'thib','Trần Thị Buoi', 2, 2 ),
('levanc@gmail.com', 'vanc','Lê Văn Canh', 3, 3),
('phamthandd@gmail.com','thanhd','Phạm Thành Duong', 4, 4),
('hoangthie@gmail.com', 'thie','Hoàng Thị Em', 5, 2);
SELECT * FROM ACCOUNT;
-- table 4 Group 
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`(
group_id INT PRIMARY KEY AUTO_INCREMENT,
group_name VARCHAR(100),
creator_id INT,
creator_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO `group`(group_name, creator_id)
VALUES
('ff team',1),
('lq team',2),
('pupg team',3);

SELECT * FROM `group`;
-- table 5 GroupAccount 
DROP TABLE IF EXISTS groupaccount;
CREATE TABLE groupaccount(
group_id INT, 
account_id INT,
join_date DATETIME DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_Group_Gro FOREIGN KEY (Group_id) REFERENCES `Group`(Group_id)
);
INSERT INTO groupaccount(group_id, account_id)
VALUES
(1,1),
(1,2),
(2,1);
select * from groupaccount;
-- table 6 TypeQuestion  
DROP TABLE IF EXISTS type_question;
CREATE TABLE type_question (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50)
);

INSERT INTO type_question(type_name)
VALUES
    ('hongphu'),
    ('deptrais1tg');

SELECT * FROM type_question;
-- tanle 7 CategoryQuestion
DROP TABLE IF EXISTS category_question;
CREATE TABLE category_question (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) 
);
INSERT INTO category_question(category_name)
VALUES
    ('Java'),
    ('.NET'),
    ('SQL'),
    ('Postman'),
    ('Ruby');

SELECT * FROM category_question;
-- table 8 Question
DROP TABLE IF EXISTS question;
CREATE TABLE question (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    content TEXT ,
    category_id INT ,
    type_id INT,
    creator_id INT ,
    create_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_ques_Cat FOREIGN KEY (Category_id) REFERENCES CategoryQuestion(Category_id),
CONSTRAINT fk_ques_Typ FOREIGN KEY (Type_id) REFERENCES TypeQuestion(Type_id)
);
INSERT INTO question
    (content, category_id, type_id, creator_id)
VALUES
    ('Java là gì?', 1, 2, 1),
    ('SQL dùng để làm gì?', 3, 2, 2),
    ('REST API là gì?', 4, 1, 3),
    ('Lập trình .NET là gì?', 2, 2, 4);

SELECT * FROM question;
-- tapble 9 Answer  
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
    answer_id INT  PRIMARY KEY AUTO_INCREMENT,
    content TEXT ,
    question_id INT ,
    is_correct BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_ans_ques FOREIGN KEY (Question_id) REFERENCES Question(Question_id)

);
INSERT INTO answer
    (content, question_id, is_correct)
VALUES
    ('Java là một ngôn ngữ lập trình', 1, Đúng),
    ('Java là một hệ điều hành', 1, Sai),
    ('SQL dùng để làm việc với cơ sở dữ liệu', 2, Đúng),
    ('SQL là một hệ điều hành', 2, Sai),
    ('REST API là một kiểu kiến trúc cho web service', 3, Đúng);

SELECT * FROM answer;
-- table 10 Exam  
DROP TABLE IF EXISTS exam;
CREATE TABLE exam (
    exam_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(100),
    title VARCHAR(100),
    category_id INT ,
    duration INT ,
    creator_id INT NOT NULL,
    create_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
	 CONSTRAINT fk_Ex_Cat FOREIGN KEY (Category_id) REFERENCES CategoryQuestion(Category_id)
);
INSERT INTO exam
    (code, title, category_id, duration, creator_id)
VALUES
    ('EX001', 'Java Basic Test', 1, 60, 1),
    ('EX002', 'SQL Basic Test', 3, 45, 2),
    ('EX003', 'Postman Test', 4, 30, 3);

SELECT * FROM exam;
-- table 11 ExamQuestion 
DROP TABLE IF EXISTS exam_question;

CREATE TABLE exam_question (
    exam_id INT,
    question_id INT,
CONSTRAINT fk_eq_ex FOREIGN KEY (Exam_id) REFERENCES Exam(Exam_id),
CONSTRAINT fk_eq_ques FOREIGN KEY (Question_id) REFERENCES Question(Question_id)
);

INSERT INTO exam_question(exam_id, question_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (2, 3),
    (3, 3),
    (3, 4);

SELECT * FROM exam_question;

