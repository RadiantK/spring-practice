create user 'spring5'@'localhost' identified by 'spring5';
grant all privileges on spring5fs.* to 'spring5'@'localhost';

create database spring5 character set=utf8;

create table spring5fs.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(255),
    PASSWORD varchar(100),
    NAME varchar(100),
    REGDATE datetime,
    unique key (EMAIL) 
) engine=InnoDB character set = utf8;
