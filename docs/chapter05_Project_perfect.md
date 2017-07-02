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

### 39登录检查操作

​	现在的页面表示，每个用户都可以直接访问相关jsp页面这种方式不可取。在程序中编写拦截器。实现非法用户拦截操作。

1. 定义AdminLoginFilter.java

   ```java
   @WebFilter({"/pages/back/index.jsp","/pages/back/classes/*","/pages/back/student/*"})
   ```

   ```java
   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpSession ses = request.getSession();
       if (ses.getAttribute("email") != null){
           filterChain.doFilter(servletRequest, servletResponse);
       }else{
           request.setAttribute("msg","你是非法用户，不能操作");
           request.setAttribute("path", "/login.jsp");
           request.getRequestDispatcher("/pages/forward.jsp").forward(servletRequest, servletResponse);
       }
   }
   ```

### 40服务器端验证操作

​	在不破坏已有代码基础上实现验证操作。要验证的数据类型：string,int,data

1. 拦截器基础结构

   ```java
   package cn.ylcto.util.interceptor;

   import org.springframework.web.servlet.HandlerInterceptor;
   import org.springframework.web.servlet.ModelAndView;

   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;

   /**
    * Created by kangkang on 2017/7/2.
    */
   public class ValidatorsInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
           return false;
       }

       @Override
       public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

       }

       @Override
       public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

       }
   }
   ```

2. 配置拦截器

   ```xml
   <mvc:interceptors>
      <mvc:interceptor>
         <mvc:mapping path="/pages/**/*.action"/>
         <bean class="cn.ylcto.util.interceptor.ValidatorsInterceptor" />
      </mvc:interceptor>
   </mvc:interceptors>
   ```

3. 定义要验证的资源文件

   ```xml
   <property name="basenames">
      <array>
         <value>Messages</value>
         <value>Pages</value>
         <value>Validators</value>
      </array>
   </property>
   ```

   ```properties
   StudentAction.insert=sid:string|name:string|age:number|address:string
   ```

4. 取得要验证的数据

   sid:string|name:string|age:number|address:string

   ```java
   String pkey = handlerMethod.getBean().getClass().getSimpleName()+"."+handlerMethod.getMethod().getName();
           String validatorValue = this.messageSource.getMessage(pkey,null, Locale.getDefault());
   ```

5. 编写验证规则

   ```java
   package cn.ylcto.util.tools;

   import javax.servlet.http.HttpServletRequest;

   /**
    * Created by kangkang on 2017/7/2.
    */
   public class Validator {
       /**
        * 验证操作
        * @param request
        * @param rule
        * @return
        */
       public boolean validate(HttpServletRequest request,String rule){
           boolean flag = true;
           String result[] = rule.split("\\|");
           for (int x = 0; x < result.length; x++){
               String temp[] = result[x].split(":");
               String value = request.getParameter(temp[0]);
               if (value != null){
                   if ("string".equals(temp[1])){
                       flag = this.validateString(value);
                   }else if ("number".equals(temp[1])){
                       flag = this.validateNumber(value);
                   }else if ("date".equals(temp[1])){
                       flag = this.validateDate(value);
                   }
                   if (flag == flag){
                       request.setAttribute(temp[0],"ruleError");
                   }
               }else{
                   flag = false;
                   request.setAttribute(temp[0],"valueError");
               }
           }
           return flag;
       }
       public boolean validateString(String str){
           if (str == null || "".equals(str)){
               return false;
           }
           return true;
       }
       public boolean validateNumber(String str){
           if (this.validateString(str)){ //表示有内容存在
               return str.matches("\\d+(\\.\\d+)?");
           }
           return false;
       }
       public boolean validateDate(String str){
           if (this.validateString(str)){
               if (str.matches("\\d{4}-\\d{2}-\\d{2}")){
                   return true;
               }else{
                   return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
               }
           }
           return false;
       }
   }
   ```

6. 使用拦截器实现验证操作

   ```java
   public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
           HandlerMethod handlerMethod = (HandlerMethod) o;
   //        System.out.println("对象："+handlerMethod.getBean().getClass().getSimpleName());
   //        System.out.println("方法："+handlerMethod.getMethod().getName());
           try {
               String vkey = handlerMethod.getBean().getClass().getSimpleName()+"."+handlerMethod.getMethod().getName();
               String pkey = handlerMethod.getBean().getClass().getSimpleName()+"."+handlerMethod.getMethod().getName() +".error";
               String validatorValue = this.messageSource.getMessage(vkey,null, Locale.getDefault());
               String pagesValue = this.messageSource.getMessage(pkey,null, Locale.getDefault());
               if (validatorValue != null){
                   if (new Validator().validate(httpServletRequest,validatorValue)){
                       return true;
                   }else{
                       httpServletRequest.getRequestDispatcher(pagesValue).forward(httpServletRequest,httpServletResponse);
                   }
               }
           }catch (Exception e){}
           return true;
       }

       @Override
       public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

       }
   ```