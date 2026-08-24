--     EXERCISE 1: JOIN  
-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ
 SELECT *
 FROM account acc 
 JOIN department dep ON acc.department_id = dep.department_id;
-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010  
SELECT *
FROM account 
WHERE created_date >'20/12/2010';
-- Question 3: Viết lệnh để lấy ra tất cả các developer  
SELECT *
FROM account acc
JOIN position pos ON acc.position_id = pos.position_id
WHERE pos.position_name = 'Dev';
-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên
 SELECT dep.department_id, dep.department_name, count(acc.account_id) as so_nhan_vien
 FROM account acc 
 JOIN department dep ON acc.department_id = dep.department_id
 GROUP BY dep.department_id, dep.department_name
 HAVING count(acc.account_id) > 3;
-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất 
SELECT que.question_id, que.content, count(eq.exam_id) AS so_lan_su_dung
FROM question que
JOIN exam_question eq ON eq.question_id = que.question_id
GROUP BY que.question_id, que.content
ORDER BY so_lan_su_dung DESC
LIMIT 1;
-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question 
SELECT  cat.category_id,  cat.category_name, count(que.question_id) AS so_luong_question
FROM category_question cat
LEFT JOIN question que ON cat.category_id = que.category_id
GROUP BY cat.category_id,  cat.category_name;
-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam 
SELECT que.question_id, que.content, count(eq.exam_id) so_luong_exam
FROM question que 
LEFT JOIN exam_question eq ON que.question_id = eq.question_id
GROUP BY que.question_id, que.content;
-- Question 8: Lấy ra Question có nhiều câu trả lời nhất 
SELECT que.question_id, que.content, count(an.answer_id) AS so_luong_cau_tra_loi
FROM question que 
JOIN answer an ON que.question_id = an.question_id
GROUP BY que.question_id, que.content
ORDER BY so_luong_cau_tra_loi
LIMIT 1;
-- Question 9: Thống kê số lượng account trong mỗi group 
select g.group_id, g.group_name, count(gra.account_id) as so_luong_account
from group_table g
left join group_account gra on g.group_id = gra.group_id
group by g.group_id, g.group_name;
-- Question 10: Tìm chức vụ có ít người nhất  
SELECT pos.position_id, pos.position_name, count(acc.account_id) AS so_nguoi
FROM position pos
LEFT JOIN account acc ON pos.position_id = acc.position_id
GROUP BY pos.position_id, pos.position_name
ORDER BY so_nguoi ASC
LIMIT 1;
-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM 
SELECT dep.department_name,
    (SELECT COUNT(*)
     FROM account acc 
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'Dev') AS so_dev,
    (SELECT COUNT(*)
     FROM account acc 
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'Test') AS so_test,
    (SELECT COUNT(*)
     FROM account acc
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'Scrum Master') AS so_scrum_master,
    (SELECT COUNT(*)
     FROM account acc 
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'PM') AS so_pm
FROM department dep;
-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, … 
SELECT que.question_id, que.content, typ.type_name, acc.full_name AS nguoi_tao, ans.content AS cau_tra_loi
FROM question que
JOIN type_question typ ON que.type_id = typ.type_id
JOIN account acc ON que.creator_id = acc.account_id
LEFT JOIN answer ans ON que.question_id = ans.question_id;
-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm 
SELECT tq.type_id, tq.type_name, count(q.question_id) AS so_luong_question
FROM type_question tq
LEFT JOIN question q ON tq.type_id = q.type_id
GROUP BY tq.type_id, tq.type_name;
-- Question 14:Lấy ra group không có account nào 
SELECT g.group_id, g.group_name
FROM group_table g
LEFT JOIN group_account ga ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;
-- Question 15: Lấy ra question không có answer nào 
SELECT q.question_id, q.content
FROM question q
LEFT JOIN answer a ON q.question_id = a.question_id
WHERE a.answer_id IS NULL;
--    EXERCISE 2: UNION
-- Question 17:  \
-- a: Lấy các account thuộc nhóm thứ 1 
-- b: Lấy các account thuộc nhóm thứ 2 
-- c: Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau 

-- a: Lấy các account thuộc nhóm thứ 1
SELECT acc.*
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 1;
 
 -- b: Lấy các account thuộc nhóm thứ 2
SELECT acc.*
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 2;

-- c: Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau 
SELECT acc.*
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 1
UNION
SELECT acc.*
FROM account acc
JOIN group_account ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 2;

-- Question 18:  
-- a: Lấy các group có lớn hơn 5 thành viên 
-- b: Lấy các group có nhỏ hơn 7 thành viên 
-- c: Ghép 2 kết quả từ câu a) và câu b) 

-- a: Lấy các group có lớn hơn 5 thành viên 
SELECT g.group_id, g.group_name,count(gra.account_id) AS so_thanh_vien
FROM group_table g
JOIN group_account gra ON g.group_id = gra.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(gra.account_id) > 5;

-- b: Lấy các group có nhỏ hơn 7 thành viên
SELECT g.group_id, g.group_name, count(gra.account_id) AS so_thanh_vien
FROM group_table g
JOIN group_account gra ON g.group_id = gra.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(gra.account_id) < 7;

-- c: Ghép 2 kết quả từ câu a) và câu b)
SELECT g.group_id, g.group_name,count(gra.account_id) AS so_thanh_vien
FROM group_table g
JOIN group_account gra ON g.group_id = gra.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(gra.account_id) > 5
UNION
SELECT g.group_id, g.group_name, count(gra.account_id) AS so_thanh_vien
FROM group_table g
JOIN group_account gra ON g.group_id = gra.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(gra.account_id) < 7;
