# SSM学生信息管理系统  



## 章节②   管理员模块开发

### 04管理员模块项目准备

1. 增加测试数据  
  ```sql
  -- 删除数据库
  DROP DATABASE ylcto;
  -- 创建删除库
  CREATE DATABASE ylcto;
  -- 使用数据库
  USE ylcto;
  -- 删除数据表
  DROP TABLE admin;
  DROP TABLE classes;
  DROP TABLE sutdent;

  -- 创建数据表
  -- 1、创建管理员表
  CREATE TABLE admin(
     email                VARCHAR(50) NOT NULL,
     password             VARCHAR(32),
     lastdate             DATE,
     CONSTRAINT pk_email PRIMARY KEY (email)
  );

  -- 2、创建班级表
  CREATE TABLE classes(
     cid                  INT NOT NULL AUTO_INCREMENT,
     cname                VARCHAR(100),
     note                 TEXT,
      CONSTRAINT pk_cid PRIMARY KEY (cid)
  );

  -- 3、创建学生表
  CREATE TABLE student(
     sid                  VARCHAR(50) NOT NULL,
     cid                  INT,
     name                 VARCHAR(50),
     age                  INT,
     sex                  INT,
     address              TEXT,
     CONSTRAINT pk_sid PRIMARY KEY (sid),
     CONSTRAINT fk_cid FOREIGN KEY (cid) REFERENCES classes(cid) ON DELETE SET NULL
  );

  -- 增加测试数据
  -- id:ylcto@163.com password:ylcto
  INSERT INTO admin(email, password,lastdate) VALUES ('ylcto@163.com','22BB09850349B763292456715CC5E25F','1999-9-9');
  -- 提交
  COMMIT;
  ```
2. 编写公共DAO(IDAO.java)  
  ```java
  package cn.ylcto.student.dao;
  import java.sql.SQLException;
  import java.util.List;
  import java.util.Set;

  /**
   * 这个接口表示一个公共DAO接口
   * @param <K> 表示主键
   * @param <V> 表示要操作的对象
   */
  public interface IDAO<K,V> {
      /**
       * 实现数据增加操作
       * @param vo 表示要执行操作的对象
       * @return 成功返回true,失败返回false
       * @throws SQLException
       */
      public boolean doCreate(V vo)throws SQLException;

      /**
       * 实现数据操作
       * @param vo 表示要执行更新对象
       * @return 成功返回true,失败返回false
       * @throws SQLException
       */
      public boolean doUpdate(V vo) throws SQLException;

      /**
       * 实现数据批量删除
       * @param ids 表示要执行删除的数据集合
       * @return 成功返回true,失败返回false
       * @throws SQLException
       */
      public boolean doRemove(Set<?> ids)throws SQLException;

      /**
       * 根据用户提供的id进行查询
       * @param id 表使用执行查询的行
       * @return 查询成功返回该数据行中的记录，失败返回null
       * @throws SQLException
       */
      public V findById(K id)throws SQLException;

      /**
       * 实现数据全部查询
       * @return 成功返回全部数据，失败返回null
       * @throws SQLException
       */
      public List<V> findAll()throws SQLException;

      /**
       * 实现数据分页操作
       * @param column 表示要执行查询列
       * @param keyWord 表示查询关键字
       * @param currentPage 表示当前页
       * @param lineSize 表示每页显示记录数
       * @return 成功返回满足条件的数据，失败返回null
       * @throws SQLException
       */
      public List<V> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize)throws SQLException;

      /**
       * 实现数据量统计操作
       * @param column 表示要执行统计列
       * @param keyWord 表示统计查询关键字
       * @return 成功返回数据量，失败返回 0
       * @throws SQLException
       */
      public Integer getAllCount(String column, String keyWord)throws SQLException;
  }
  ```

### 05管理员登录数据层实现

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
  ​```xml
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

### 06服务层实现并编写JUNIT测试

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
2. 编写实现类  
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
3. 编写测试  
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

### 07JUNIT测试报错，正在修复ing

![](../images/22.jpg)  
​	

	2017.06.19-23  今天跟优乐编程的老师沟通了下，还是没能解决问题
	2017.06.24  今天重新把项目弄了一遍，就可以了，无语.....
### 08JUNIT修复完毕

### 09管理员登录控制层实现

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
2. 编写工具类  
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
3. 编写实现管理员登录
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
4. 编写资源文件
```properties
# {0}登录成功！
admin.insert.success ={0}\u767b\u5f55\u6210\u529f\uff01
# {0}登录失败！
admin.insert.failure ={0}\u767b\u5f55\u5931\u8d25\uff01

pages.forward=/pages/forward.jsp

admin.login.success=/pages/back/classes/classes_insert.jsp
admin.login.failure=/login.jsp
```
### 10管理员登录前台页面编写

在webapp目录下建立一个pages/back的文件夹
导入相关的js和css文件
需要导入login.html页面
需要在页面中导入basepath
将forward.jsp页面拷贝到pages目录下

1. 修改login.jsp页面  
  ```html
  <form role="form" action="<%=basePath%>pages/back/admin_login.action" method="post" id="loginForm">
  <fieldset>
      <div class="form-group">
          <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus value="ylcto@163.com">
      </div>
      <div class="form-group">
          <input class="form-control" placeholder="Password" name="password" type="password" value="ylcto">
      </div>
      <button type="submit" class="btn btn-success btn-block">登录</button>
  </fieldset>
  </form>
  ```

2. 验证登录数据是否存在
  ```javascript
  $(function() {  // 在页面加载的时候执行
      $("#loginForm").validate({ // 定义验证规则
          debug: true,  // 采用调试模式，表单不会自动提交
          submitHandler: function (form) {    // 当前表单对象
              form.submit(); // 手工提交，如果不需要手工提交，可以在此处进行异步处理
          },
          rules: {   // 为每一个表单编写验证规则

              "email": {
                  required: true,  // 此字段不允许为空
                  email:true  // 表示为email格式
              },
              "password": {
                  required: true,  // 此字段不允许为空
                  minlength:4
              }
          }
      });
  });
  ```
  基于jquery验证框架完成的验证操作相对简单，如果不会使用只需要修改admin_login.js文件即可。  

### 11更新最后一次登录日期（数据层实现）

实现最后一次登录日期更新

1. 修改遗留问题  
  ```java
  request.getSession().setAttribute("email",vo.getEmail());
  request.getSession().setAttribute("lastdate",new SimpleDateFormat("yyyy-MM-dd").format(vo.getLastdate())); //取得最后一次登录日期操作
  ```
2. 编写adminMapper.xml  
  ```xml
      <!--更新最后一次登录日期-->
      <update id="doUpdateLastDate" parameterType="Admin">
          UPDATE admin SET lastdate=#{lastdate} WHERE email=#{email}
      </update>
  ```
3. 编写AdminDAOImpl.java
  ```java
  @Override
      public boolean doUpdate(Admin vo) throws SQLException {
          return super.getSqlSession().update("AdminNS.doUpdateLastDate",vo) > 0;
      }
  ```

### 12更新最后一次登录日期（业务层实现）

1. 在登录业务层实现更新操作  
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