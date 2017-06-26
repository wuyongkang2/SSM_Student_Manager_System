# 2017.06.15 06服务层实现并编写JUNIT测试
1. 编写服务层接口  
	```java
	package cn.ylcto.student.service;
	import cn.ylcto.student.vo.Admin;
	
	public interface IAdminService {
	    /**
	     * 实现管理员登录
	     * @param vo 包含要执行登录检查的字段 Email password
	     * @return
	     * @throws Exception
	     */
	    public Admin login(Admin vo)throws Exception;
	}	
	```
1. 编写实现类  
	```java
	package cn.ylcto.student.service.impl;

	import cn.ylcto.student.dao.IAdminDAO;
	import cn.ylcto.student.service.IAdminService;
	import cn.ylcto.student.vo.Admin;
	import org.springframework.stereotype.Service;
	import javax.annotation.Resource;
	import java.util.Date;
	
	@Service
	public class AdminServiceImpl implements IAdminService {
	    @Resource
	    private IAdminDAO adminDAO;
	    @Override
	    public Admin login(Admin vo) throws Exception {
	        Admin admin = this.adminDAO.findLogin(vo);
	        if (admin != null){ // 表示登录成功
	            vo.setLastdate(new Date());
	            this.adminDAO.doUpdate(vo);
	        }
	        return admin;
	    }
	}
	```
1. 编写测试  
	```java
	package cn.ylcto.test;

	import cn.ylcto.student.service.IAdminService;
	import cn.ylcto.student.vo.Admin;
	import junit.framework.TestCase;
	import org.junit.Test;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
	
	
	public class AdminServiceTest {
	    private static ApplicationContext ctx ;
	    private static IAdminService adminService;
	
	    static {
	        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	        adminService = ctx.getBean("adminServiceImpl",IAdminService.class);
	    }
	
	    @Test
	    public void login() throws Exception {
	        Admin admin = new Admin();
	        admin.setEmail("ylcto@163.com");
	        admin.setPassword("22BB09850349B763292456715CC5E25F");
	        System.out.println(this.adminService.login(admin));
	    }
	}
	```