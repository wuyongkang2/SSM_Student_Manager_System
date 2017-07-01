# SSM学生信息管理系统  



## 章节⑤   项目功能完善

### 38实现注销功能

1. 修改导航

   ```html
   <a href="/pages/back/admin_logout.action"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
   ```

2.  实现注销操作

   ```java
   @RequestMapping(value = "admin_logout")
   public ModelAndView logout(HttpServletRequest request){
       ModelAndView mav = new ModelAndView(super.getResource("pages.forward"));
       request.getSession().invalidate(); //表示session失效
       super.setMsgAndPath(mav,"admin.logout.success","admin.logout.failure");
       return mav;
   }
   ```

3. 编写资源文件

   ```properties
   admin.logout.failure=/login.jsp
   ```

   ```properties
   # {0}注销成功！
   admin.logout.success={0}\u6ce8\u9500\u6210\u529f\uff01
   ```