-- 科目データ追加用
insert into subjects (subject) values 
('英語試験'), 
('中国語試験'), 
('韓国語試験');

-- 等級データ追加用
insert into grades (grade) values 
('初級'), 
('中級'), 
('上級');

-- 会場データ追加用
insert into places (place) values 
('東京'), 
('名古屋'), 
('大阪'); 

-- 受験料データ追加用
insert into fees (subject_id, grade_id, fee) values 
(1, 1, 5000), 
(1, 2, 6000), 
(1, 3, 7000), 
(2, 1, 5000), 
(2, 2, 6000), 
(2, 3, 7000), 
(3, 1, 5000), 
(3, 2, 6000), 
(3, 3, 7000);

-- 管理者アカウント設定用
-- IDの指定を行ってください
update accounts set 
role = 1 
where id = 