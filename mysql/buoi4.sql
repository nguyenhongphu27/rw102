-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
DROP VIEW IF EXISTS view_nhan_vien_sales;
CREATE VIEW view_nhan_vien_sales AS
SELECT *
FROM account 
WHERE department_id = (
                        SELECT department_id
						FROM department
						WHERE department_name = 'Sales'
);
-- xem dữ liệu bảng
SELECT * FROM view_nhan_vien_sales;
-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
DROP VIEW IF EXISTS view_account_vao_nhieu_group_nhat;
CREATE VIEW view_account_vao_nhieu_group_nhat AS
SELECT acc.account_id,
       acc.full_name,
       acc.email,
       COUNT(gra.group_id) AS so_group
FROM group_account gra
JOIN account acc ON acc.account_id = gra.account_id
GROUP BY acc.account_id, acc.full_name, acc.email
HAVING COUNT(gra.group_id) = (
							  SELECT COUNT(1)
                              FROM group_account
                              GROUP BY account_id
                              ORDER BY COUNT(1) DESC
                              LIMIT 1
);
SELECT * FROM view_account_vao_nhieu_group_nhat;
-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
-- 1: tạo view chứa những content quá dài
DROP VIEW IF EXISTS view_content_qua_dai;
CREATE VIEW view_content_qua_dai AS
SELECT *
FROM question
WHERE LENGTH(content) - LENGTH(REPLACE(content, ' ', '')) + 1 > 300;
-- 2: xóa các content được coi là quá dài 
DELETE FROM question
WHERE LENGTH(content) > 300;
-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
DROP VIEW IF EXISTS view_phong_ban_nnvn;
CREATE VIEW view_phong_ban_nnvn AS
SELECT dep.*, COUNT(acc.account_id) AS so_nhan_vien
FROM department dep
LEFT JOIN account acc ON dep.department_id = acc.department_id
GROUP BY dep.department_id
HAVING COUNT(acc.account_id) >= ALL (
									 SELECT COUNT(acc.account_id)
									 FROM department dep
									 LEFT JOIN account acc
									 ON dep.department_id = acc.department_id
								     GROUP BY dep.department_id
);
SELECT * FROM  view_phong_ban_nnvn;
-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo
DROP VIEW IF EXISTS view_cac_cau_hoi_do_user_tao;
CREATE VIEW view_cac_cau_hoi_do_user_tao AS
SELECT *
FROM question
WHERE creator_id IN (
				      SELECT account_id
					  FROM account
					  WHERE full_name LIKE 'Nguyễn%'
);
-- xem dữ liệu bangr
SELECT * FROM view_cac_cau_hoi_do_user_tao