# 2017.06.26 14班级增加（业务层实现与JUNIT测试）

1. 定义服务层接口，名称为IClassesService.java
	```java
	package cn.ylcto.student.service;
	
	import cn.ylcto.student.vo.Classes;
	
	/**
	 * Created by kangkang on 2017/6/26.
	 */
	public interface IClassesService {
	    /**
	     *  实现班级数据增加操作
	     * @param vo 表示要执行增加操作的vo对象
	     * @return 成功返回true,失败返回false
	     * @throws Exception
	     */
	public boolean insert(Classes vo) throws Exception;
	}
	```
1. 编写接口实现类，名称为ClassesServiceImpl.java  
	```java
	package cn.ylcto.student.service.impl;
	
	import cn.ylcto.student.dao.IClassesDAO;
	import cn.ylcto.student.service.IClassesService;
	import cn.ylcto.student.vo.Classes;
	import org.springframework.stereotype.Service;
	import javax.annotation.Resource;
	
	/**
	 * Created by kangkang on 2017/6/26.
	 */
	@Service
	public class ClassesServiceImpl implements IClassesService {
	    @Resource
	    private IClassesDAO classesDAO;
	    @Override
	    public boolean insert(Classes vo) throws Exception {
	        return this.classesDAO.doCreate(vo);
	    }
	}
	```
1. 编写测试数据  
	```java
	package cn.ylcto.test;
	
	import cn.ylcto.student.service.IAdminService;
	import cn.ylcto.student.service.IClassesService;
	import cn.ylcto.student.vo.Admin;
	import cn.ylcto.student.vo.Classes;
	import junit.framework.TestCase;
	import org.junit.Test;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
	
	
	public class ClassesServiceTest {
	    private static ApplicationContext ctx ;
	    private static IClassesService classesService;
	
	    static {
	        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	        classesService = ctx.getBean("classesServiceImpl",IClassesService.class);
	    }
	    @Test
	    public void insert()throws Exception{
	        Classes vo = new Classes();
	        vo.setCname("YL003");
	        vo.setNote("这是一个新开的java培训班");
	        TestCase.assertTrue(this.classesService.insert(vo));
	    }
	}
	```  