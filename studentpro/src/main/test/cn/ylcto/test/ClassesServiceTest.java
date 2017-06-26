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
