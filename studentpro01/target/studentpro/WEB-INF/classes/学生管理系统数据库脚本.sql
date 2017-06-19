-- 删除数据库
DROP DATABASE ylcto;
-- 创建删除库
CREATE DATABASE ylcto;
-- 使用数据库
USE ylcto;
-- 删除数据表
DROP TABLE admin;
DROP TABLE classes;
DROP TABLE sutdent;

-- 创建数据表
-- 1、创建管理员表
CREATE TABLE admin(
   email                VARCHAR(50) NOT NULL,
   password             VARCHAR(32),
   lastdate             DATE,
   CONSTRAINT pk_email PRIMARY KEY (email)
);

-- 2、创建班级表
CREATE TABLE classes(
   cid                  INT NOT NULL AUTO_INCREMENT,
   cname                VARCHAR(100),
   note                 TEXT,
    CONSTRAINT pk_cid PRIMARY KEY (cid)
);

-- 3、创建学生表
CREATE TABLE student(
   sid                  VARCHAR(50) NOT NULL,
   cid                  INT,
   name                 VARCHAR(50),
   age                  INT,
   sex                  INT,
   address              TEXT,
   CONSTRAINT pk_sid PRIMARY KEY (sid),
   CONSTRAINT fk_cid FOREIGN KEY (cid) REFERENCES classes(cid) ON DELETE SET NULL
);

-- 增加测试数据
-- id:ylcto@163.com password:ylcto
INSERT INTO admin(email, password,lastdate) VALUES ('ylcto@163.com','22BB09850349B763292456715CC5E25F','1999-9-9');
-- 提交
COMMIT;