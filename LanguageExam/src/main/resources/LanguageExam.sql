create table accounts ( 
id bigint not null auto_increment primary key, 
name varchar(50) not null, 
mail varchar(256) not null unique, 
pass varchar(256) not null, 
pref varchar(50) not null, 
city varchar(50) not null, 
town varchar(50) not null, 
building varchar(50) , 
role tinyint not null default 0 ) 

create table subjects (
id bigint not null primary key, 
subject varchar(50) not null )

create table grades (
id bigint not null primary key, 
grade varchar(59) not null ) 

create table fees (
id bigint not null primary key, 
subject_id bigint not null, 
grade_id bigint not null, 
fee bigint not null ) 

create table exam_infos (
id bigint not null auto_increment primary key, 
subject_id bigint not null, 
grade_id bigint not null, 
exam_date date not null, 
old_date date, 
place_id bigint not null, 
comments varchar(100), 
cancel tinyint not null default 0 ) 

create table infos (
id bigint not null primary key, 
post_date date not null, 
title varchar(50) not null, 
content varchar(500) not null )

alter table infos modify column id bigint not null auto_increment;

create table places (
id bigint not null primary key auto_increment, 
place varchar(50) not null )