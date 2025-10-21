-- accountsテーブル作成用
create table accounts (
id bigint not null auto_increment primary key, 
name varchar(50) not null, 
mail varchar(256) not null unique, 
pass varchar(256) not null, 
pref varchar(50) not null, 
city varchar(50) not null, 
town varchar(50) not null, 
building varchar(50) , 
role tinyint not null default 0 );

-- subjectsテーブル作成用
create table subjects (
id bigint not null auto_increment primary key, 
subject varchar(50) not null );

-- gradesテーブル作成用
create table grades (
id bigint not null auto_increment primary key, 
grade varchar(50) not null ); 

-- placesテーブル作成用
create table places (
id bigint not null auto_increment primary key,
place varchar(50) not null ); 

-- feesテーブル作成用
create table fees (
id bigint not null auto_increment primary key, 
subject_id bigint not null, 
grade_id bigint not null, 
fee bigint ); 

-- exam_infosテーブル作成用
create table exam_infos ( 
id bigint not null auto_increment primary key, 
subject_id bigint not null, 
grade_id bigint not null, 
exam_date date not null, 
old_date date, 
place_id bigint not null, 
comments varchar(100), 
cancel tinyint not null default 0 ); 

-- bookingsテーブル作成用
create table bookings ( 
id bigint not null auto_increment primary key, 
account_id bigint not null, 
exam_id bigint not null ); 

-- infosテーブル作成用
create table infos ( 
id bigint not null auto_increment primary key, 
post_date date not null, 
title varchar(50) not null, 
content varchar(500) not null );