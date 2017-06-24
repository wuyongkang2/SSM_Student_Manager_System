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
