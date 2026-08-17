DROP TABLE IF EXISTS department;
CREATE TABLE department(
   department_id		INT,
   department_name 		VARCHAR(50)
);

-- user: id, fullname, user_name, brith_of_date, gender
(
	id 				INT,CREATE TABLE user,
	full_name 		VARCHAR(50),
	birth_of_date 	DATE,
	gender 			ENUM('male','female'),
	username 		VARCHAR(50)
);

SELECT *
FROM rw_102;




INSERT INTO department(department_id, department_name)
VALUES (1,'sale'),
		(2,'marketing'),
		(3,'bao ve');

DROP DATABASE IF EXISTS Position;
CREATE DATABASE Position;

