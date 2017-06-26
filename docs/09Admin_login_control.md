# 2017.06.24 09管理员登录控制层实现
在编写控制层之前，需要编写一个工具类，方便以后的代码更加快捷  

1. 定义Action类  
	```java
	package cn.ylcto.student.action;
	
	import cn.ylcto.student.vo.Admin;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.servlet.ModelAndView;
	
	/**
	 * Created by kangkang on 2017/6/24.
	 */
	@Controller
	@RequestMapping(value = "/pages/back/*")
	public class AdminLoginAction{
	    
	    public ModelAndView login(Admin admin){
	         ModelAndView mav = new ModelAndView();
	        return mav;
	    }
	}
	```
1. 编写工具类  
	```java
	package cn.ylcto.util.action;

	import org.springframework.context.MessageSource;
	import org.springframework.web.servlet.ModelAndView;
	import javax.annotation.Resource;
	import java.util.Locale;
	
	/**
	 * Created by kangkang on 2017/6/24.
	 */
	public abstract class DefaultAction {
	    @Resource
	    private MessageSource messageSource;
	
	    /**
	     * 可以根据key取得资源的内容
	     * @param key 表示要读取的key
	     * @param param 表示设置的参数
	     * @return
	     */
	    public String getResource(String key,String ...param){
	        return this.messageSource.getMessage(key,param, Locale.getDefault());
	    }
	
	    /**
	     * 设置提示信息与跳转路径
	     * @param mav
	     * @param msg
	     * @param path
	     */
	    public void setMsgAndPath(ModelAndView mav,String msg,String path){
	        if (mav != null){
	             if (this.getText() != null){
	                 String[] result = this.getText().split("\\|");
	                 mav.addObject("msg",this.messageSource.getMessage(msg,result,Locale.getDefault()));
	             }else{
	                 mav.addObject("msg",this.messageSource.getMessage(msg,null,Locale.getDefault()));
	             }
	            mav.addObject("path",this.messageSource.getMessage(path,null,Locale.getDefault()));
	        }
	    }
	
	    public abstract String getText();
	}
	```
1. 编写实现管理员登录
	```java
	package cn.ylcto.student.action;
	
	import cn.ylcto.student.service.IAdminService;
	import cn.ylcto.student.vo.Admin;
	import cn.ylcto.util.MD5Code;
	import cn.ylcto.util.action.DefaultAction;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.servlet.ModelAndView;
	import javax.annotation.Resource;
	import javax.servlet.http.HttpServletRequest;
	
	/**
	 * Created by kangkang on 2017/6/24.
	 */
	@Controller
	@RequestMapping(value = "/pages/back/*")
	public class AdminLoginAction extends DefaultAction {
	    @Resource
	    private IAdminService adminService;
	    @Autowired
	    private HttpServletRequest request;
	
	    @RequestMapping(value = "admin_login")
	    public ModelAndView login(Admin admin){
	         ModelAndView mav = new ModelAndView(super.getResource("pages.forward"));
	        try {
	            //实现登录密码加盐操作
	            admin.setPassword(new MD5Code().getMD5ofStr(admin.getPassword()+admin.getEmail()));
	            Admin vo = this.adminService.login(admin); //登陆成功还要取得最后一次登陆日期
	            if (vo != null){
	                super.setMsgAndPath(mav,"admin.insert.success","admin.login.success");
	                request.getSession().setAttribute("lastdate",vo.getLastdate()); //取得最后一次登录日期操作
	            }else{
	                super.setMsgAndPath(mav,"admin.insert.failure","admin.login.failure");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mav;
	    }
	}
	```
1. 编写资源文件
```properties
# {0}登录成功！
admin.insert.success ={0}\u767b\u5f55\u6210\u529f\uff01
# {0}登录失败！
admin.insert.failure ={0}\u767b\u5f55\u5931\u8d25\uff01

pages.forward=/pages/forward.jsp

admin.login.success=/pages/back/classes/classes_insert.jsp
admin.login.failure=/login.jsp
```