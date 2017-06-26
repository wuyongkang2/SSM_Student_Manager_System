# 2017.06.12-13 04管理员模块项目准备  
1. 增加测试数据  
	```sql
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
	```
1. 编写公共DAO(IDAO.java)  
	```java
	package cn.ylcto.student.dao;
	import java.sql.SQLException;
	import java.util.List;
	import java.util.Set;
	
	/**
	 * 这个接口表示一个公共DAO接口
	 * @param <K> 表示主键
	 * @param <V> 表示要操作的对象
	 */
	public interface IDAO<K,V> {
	    /**
	     * 实现数据增加操作
	     * @param vo 表示要执行操作的对象
	     * @return 成功返回true,失败返回false
	     * @throws SQLException
	     */
	    public boolean doCreate(V vo)throws SQLException;
	
	    /**
	     * 实现数据操作
	     * @param vo 表示要执行更新对象
	     * @return 成功返回true,失败返回false
	     * @throws SQLException
	     */
	    public boolean doUpdate(V vo) throws SQLException;
	
	    /**
	     * 实现数据批量删除
	     * @param ids 表示要执行删除的数据集合
	     * @return 成功返回true,失败返回false
	     * @throws SQLException
	     */
	    public boolean doRemove(Set<?> ids)throws SQLException;
	
	    /**
	     * 根据用户提供的id进行查询
	     * @param id 表使用执行查询的行
	     * @return 查询成功返回该数据行中的记录，失败返回null
	     * @throws SQLException
	     */
	    public V findById(K id)throws SQLException;
	
	    /**
	     * 实现数据全部查询
	     * @return 成功返回全部数据，失败返回null
	     * @throws SQLException
	     */
	    public List<V> findAll()throws SQLException;
	
	    /**
	     * 实现数据分页操作
	     * @param column 表示要执行查询列
	     * @param keyWord 表示查询关键字
	     * @param currentPage 表示当前页
	     * @param lineSize 表示每页显示记录数
	     * @return 成功返回满足条件的数据，失败返回null
	     * @throws SQLException
	     */
	    public List<V> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize)throws SQLException;
	
	    /**
	     * 实现数据量统计操作
	     * @param column 表示要执行统计列
	     * @param keyWord 表示统计查询关键字
	     * @return 成功返回数据量，失败返回 0
	     * @throws SQLException
	     */
	    public Integer getAllCount(String column, String keyWord)throws SQLException;
	}
	```