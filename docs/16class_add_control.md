# 2017.06.26 16班级增加（控制层实现）  

1. 编写ClassesAction.java
	```java
	package cn.ylcto.student.action;
	
	import cn.ylcto.student.service.IClassesService;
	import cn.ylcto.student.vo.Classes;
	import cn.ylcto.util.action.DefaultAction;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.servlet.ModelAndView;
	import javax.annotation.Resource;
	
	/**
	 * Created by kangkang on 2017/6/26.
	 */
	@Controller
	@RequestMapping(value = "/pages/back/classes/*")
	public class ClassesAction extends DefaultAction {
	    @Resource
	    private IClassesService classesService;
	    @RequestMapping(value = "classes_insert")
	    public ModelAndView insert(Classes vo){
	        ModelAndView mav = new ModelAndView(super.getResource("pages.forward"));
	        try {
	            if (this.classesService.insert(vo)){
	                super.setMsgAndPath(mav,"classes.insert.success","classes.login.success");
	            }else{
	                super.setMsgAndPath(mav,"classes.insert.failure","classes.login.failure");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mav;
	    }
	    @Override
	    public String getText() {
	        return "班级";
	    }
	}
	```
2. 配置提示信息
	```properties
	# {0}增加成功！
	classes.insert.success={0}\u589e\u52a0\u6210\u529f\uff01
	# {0}增加失败！
	classes.insert.failure={0}\u589e\u52a0\u5931\u8d25\uff01
	```
3. 配置跳转路径
	```properties
	classes.login.success=/pages/back/classes/classes_insert.jsp
	classes.login.failure=/pages/back/classes/classes_insert.jsp
	```
