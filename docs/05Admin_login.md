# 2017.06.14 05管理员登录数据层实现
1. 实现数据层编写
	- 创建vo，名称为Admin.java  
	```java
	package cn.ylcto.student.vo;
	import java.io.Serializable;
	import java.util.Date;
	
	public class Admin implements Serializable {
	    private String email;
	    private String password;
	    private Date lastdate;
	
	    public String getEmail() {
	        return email;
	    }
	
	    public void setEmail(String email) {
	        this.email = email;
	    }
	
	    public String getPassword() {
	        return password;
	    }
	
	    public void setPassword(String password) {
	        this.password = password;
	    }
	
	    public Date getLastdate() {
	        return lastdate;
	    }
	
	    public void setLastdate(Date lastdate) {
	        this.lastdate = lastdate;
	    }
	}
	```
	- 编写adminMapper.xml  
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="AdminNS">
	
	    <resultMap id="adminResultMap" type="Admin">
	        <id property="email" column="email"></id>
	        <result property="password" column="password"></result>
	        <result property="lastdate" column="lastdate"></result>
	    </resultMap>
	    <!--实现登录查询操作-->
	    <select id="findLogin" parameterType="Admin" resultType="Admin">
	        SELECT email,lastdate FROM admin WHERE email=#{email} AND password=#{password}
	    </select>
	</mapper>
	- 修改mybaties.cfg.xml  
	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration   
	    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"   
	    "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
	    <!--配置别名-->
		<typeAliases>
	        <typeAlias type="cn.ylcto.student.vo.Admin" alias="Admin"/>
	    </typeAliases>
	    <mappers>
	        <mapper resource="mapper/adminMapper.xml"/>
	    </mappers>
	</configuration>
	```
	- 编写IAdminDAO.java的接口
	```java
	package cn.ylcto.student.dao;
	import cn.ylcto.student.vo.Admin;
	import java.sql.SQLException;
	
	public interface IAdminDAO extends IDAO<String,Admin> {
	    /**
	     * 实现登录查询操作
	     * @param vo 包含要执行查询的字段：（email password）
	     * @return 成功返回最后一次登录日期
	     * @throws SQLException
	     */
	    public Admin findLogin(Admin vo)throws SQLException;
	}
	```
	- 编写实现类，名称为AdminDAOImpl  
	```java
	package cn.ylcto.student.dao.impl;
	
	import cn.ylcto.student.dao.IAdminDAO;
	import cn.ylcto.student.vo.Admin;
	import org.apache.ibatis.session.SqlSessionFactory;
	import org.mybatis.spring.support.SqlSessionDaoSupport;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;
	
	import java.sql.SQLException;
	import java.util.List;
	import java.util.Set;
	@Repository
	public class AdminDAOImpl extends SqlSessionDaoSupport implements IAdminDAO {
	    @Autowired
	    public AdminDAOImpl(SqlSessionFactory sqlSessionFactory){
	        super.setSqlSessionFactory(sqlSessionFactory);
	    }
	    @Override
	    public Admin findLogin(Admin vo) throws SQLException {
	        return super.getSqlSession().selectOne("AdminNS.findLogin",vo);
	    }
	}
	```