# 2017.06.25 13班级增加模块（数据层实现）

1. 定义vo类，名称为Classes.java  
	```java
	package cn.ylcto.student.vo;
	
	import java.io.Serializable;
	
	/**
	 * Created by kangkang on 2017/6/25.
	 */
	public class Classes implements Serializable {
	    private Integer cid;
	    private String cname;
	    private String note;
	
	    public Integer getCid() {
	        return cid;
	    }
	
	    public void setCid(Integer cid) {
	        this.cid = cid;
	    }
	
	    public String getCname() {
	        return cname;
	    }
	
	    public void setCname(String cname) {
	        this.cname = cname;
	    }
	
	    public String getNote() {
	        return note;
	    }
	
	    public void setNote(String note) {
	        this.note = note;
	    }
	}
	```
1. 定义dao接口并继承IDAO.java接口  
	```java
	package cn.ylcto.student.dao;
	
	import cn.ylcto.student.vo.Classes;
	
	/**
	 * Created by kangkang on 2017/6/25.
	 */
	public interface IClassesDAO extends IDAO<Integer,Classes> {
	}
	```
1. 编写实现类  
	```java
	package cn.ylcto.student.dao.impl;
	
	import cn.ylcto.student.dao.IClassesDAO;
	import cn.ylcto.student.vo.Classes;
	import org.apache.ibatis.session.SqlSessionFactory;
	import org.mybatis.spring.support.SqlSessionDaoSupport;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;
	import java.sql.SQLException;
	import java.util.List;
	import java.util.Set;
	
	/**
	 * Created by kangkang on 2017/6/25.
	 */
	@Repository
	public class ClassesDAOImpl extends SqlSessionDaoSupport implements IClassesDAO {
	    @Autowired
	    public ClassesDAOImpl(SqlSessionFactory sqlSessionFactory){
	        super.setSqlSessionFactory(sqlSessionFactory);
	    }
	    @Override
	    public boolean doCreate(Classes vo) throws SQLException {
	        return super.getSqlSession().insert("classesNS.doCreate",vo) > 0;
	    }
	}
	```
1. 编写classesMapper.xml文件  
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="classesNS">
	   <!-- 实现数据增加  -->
	    <insert id="doCreate" parameterType="classes">
	        INSERT INTO classes(cname,note) VALUES (#{cname},#{note})
	    </insert>
	</mapper>
	```
1. 修改mybatis.cfg.xml  
	```xml
	<typeAlias type="cn.ylcto.student.vo.Classes" alias="Classes"/>
	<mapper resource="mapper/classesMapper.xml"/>
	```