insert into subjects (subject) values 
('英語試験'), 
('中国語試験'), 
('韓国語試験') 

insert into grdes (grade) 
('初級'), 
('中級'), 
('上級') 

insert into places (place) values 
('東京'), 
('名古屋'), 
('大阪') 

insert into fees (subject_id, grade_id, fee) values 
(1, 1, 5000), 
(1, 2, 6000), 
(1, 3, 

insert into exam_infos (subject_id, grade_id, exam_date, place_id) values 
(1, 1, '2025-11-01', 3) 

insert into infos (post_date, title, content) values 
(curdate(), 'テスト', 'テスト'), 
(curdate(), 'テスト', 'テスト'), 
(curdate(), 'テスト', 'テスト'), 
(curdate(), 'テスト', 'テスト'),
(curdate(), 'テスト', 'テスト'), 
(curdate(), 'テスト', 'テスト')