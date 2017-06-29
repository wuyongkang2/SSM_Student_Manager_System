# SSM学生信息管理系统  



## 章节③   班级模块开发

### 13班级增加模块（数据层实现）

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
2. 定义dao接口并继承IDAO.java接口  
  ```java
  package cn.ylcto.student.dao;

  import cn.ylcto.student.vo.Classes;

  /**
   * Created by kangkang on 2017/6/25.
   */
  public interface IClassesDAO extends IDAO<Integer,Classes> {
  }
  ```
3. 编写实现类  
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
4. 编写classesMapper.xml文件  
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
5. 修改mybatis.cfg.xml  
  ```xml
  <typeAlias type="cn.ylcto.student.vo.Classes" alias="Classes"/>
  <mapper resource="mapper/classesMapper.xml"/>
  ```

### 14班级增加（业务层实现与JUNIT测试）

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
2. 编写接口实现类，名称为ClassesServiceImpl.java  
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
3. 编写测试数据  
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
  ```


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

### 15班级增加（完善业务）

1. 编写classesMapper.xml  
  ```xml
  <!-- 查询班级名称 -->
    <select id="findByCname" parameterType="String" resultMap="classesResultMap">
        SELECT cid,cname,note FROM classes WHERE cname=#{cname}
    </select>

  <resultMap id="classesResultMap" type="Classes">
        <id property="cid" column="cid"></id>
        <result property="cname" column="cname"></result>
        <result property="note" column="note"></result>
    </resultMap>
  ```
2. 在接口中定义一个方法  
  ```java
  public interface IClassesDAO extends IDAO<Integer,Classes> {
      /**
       * 实现班级名称查询操作
       * @param cname 表示要执行查询的班级名称
       * @return
       * @throws SQLException
       */
      public Classes findByCname(String cname) throws SQLException;
  }
  ```
3. 实现接口中定义的方法  
  ```java
  @Override
      public Classes findByCname(String cname) throws SQLException {
          return super.getSqlSession().selectOne("classesNS.findByCname",cname);
      }
  ```
4. 修改班级增加的方法
  ```java
  @Service
  public class ClassesServiceImpl implements IClassesService {
      @Resource
      private IClassesDAO classesDAO;
      @Override
      public boolean insert(Classes vo) throws Exception {
          if (this.classesDAO.findByCname(vo.getCname()) == null){
              return this.classesDAO.doCreate(vo);
          }
          return false;
      }
  }
  ```

### 16班级增加（控制层实现）

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

### 17班级增加（编写页面）

1. header.jsp

   ```html
   <html lang="zh">
   <head>
       <base href="<%=basePath%>">
       <meta charset="utf-8">
       <meta http-equiv="X-UA-Compatible" content="IE=edge">
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <meta name="description" content="">
       <meta name="author" content="">
       <title>优乐在线教育学生管理系统（WWW.YLCTO.CN）</title>
       <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
       <link href="js/metisMenu/metisMenu.min.css" rel="stylesheet">
       <link href="js/dist/css/sb-admin-2.css" rel="stylesheet">
       <link href="js/morrisjs/morris.css" rel="stylesheet">
       <link href="js/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
       <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
       <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
   </head>
   <body>

   <div id="wrapper">
       <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
           <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="index.html">学生管理系统</a>
           </div>
           </li>
           <ul class="nav navbar-top-links navbar-right">
               <li class="dropdown">
                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                       <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                   </a>
                   <ul class="dropdown-menu dropdown-user">
                       <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户信息</a>
                       </li>
                       <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a>
                       </li>
                       <li class="divider"></li>
                       <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                       </li>
                   </ul>
               </li>
           </ul>
           <div class="navbar-default sidebar" role="navigation">
               <div class="sidebar-nav navbar-collapse">
                   <ul class="nav" id="side-menu">
                       <li>
                           <a href="/pages/back/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 优乐在线教育</a>
                       </li>
                       <li>
                           <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 班级管理<span class="fa arrow"></span></a>
                           <ul class="nav nav-second-level">
                               <li>
                                   <a href="/pages/back/classes/classes_insert.jsp">增加班级</a>
                               </li>
                               <li>
                                   <a href="/pages/back/classes/classes_list.action">班级列表</a>
                               </li>
                           </ul>
                       </li>
                       <li>
                           <a href="#"><i class="fa fa-edit fa-fw"></i> 学生列表<span class="fa arrow"></span></a>
                           <ul class="nav nav-second-level">
                               <li>
                                   <a href="panels-wells.html">增加学生</a>
                               </li>
                               <li>
                                   <a href="buttons.html">学生列表</a>
                               </li>
                           </ul>
                       </li>
                   </ul>
               </div>
           </div>
       </nav>
   ```

2. footer.jsp

   ```html
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <%
       String path = request.getContextPath();
       String basePath = request.getScheme() + "://"
               + request.getServerName() + ":" + request.getServerPort()
               + path +"/";
   %>
   <base href="<%=basePath%>">
   </div>
   <script src="js/jquery/jquery.min.js"></script>
   <script src="js/bootstrap/js/bootstrap.min.js"></script>
   <script src="js/metisMenu/metisMenu.min.js"></script>
   <script src="js/raphael/raphael.min.js"></script>
   <script src="js/morrisjs/morris.min.js"></script>
   <script src="js/data/morris-data.js"></script>
   <script src="js/dist/js/sb-admin-2.js"></script>
   ```

3. 编写表单

   ```html
   <form action="<%=basePath%>pages/back/classes/classes_insert.action" method="post" class="form-horizontal" id="insertForm">
           <div class="form-group">
               <label for="cname" class="control-label col-md-3">班级名称</label>
               <div class=col-md-5>
                   <input type="text" class="form-control" name="cname" id="cname" placeholder="请输入与班级名称">
               </div>
           </div>
           <div class="form-group">
               <label for="cname" class="control-label col-md-3">班级名称</label>
               <div class=col-md-5>
                   <textarea class="form-control" name="note" id="note" placeholder="请输入班级简介" ></textarea>
               </div>
           </div>
           <div class="form-group">
               <div class="col-md-4 col-md-offset-6">
                   <button type="submit" class="btn btn-success btn-sm">增加</button>
                   <button type="reset" class="btn btn-danger btn-sm">重置</button>
               </div>
           </div>
       </form>
   ```

4. 编写验证

   ```javascript
   $(function() {  // 在页面加载的时候执行
       $("#insertForm").validate({ // 定义验证规则
           debug: true,  // 采用调试模式，表单不会自动提交
           submitHandler: function (form) {    // 当前表单对象
               form.submit(); // 手工提交，如果不需要手工提交，可以在此处进行异步处理
           },
           rules: {   // 为每一个表单编写验证规则

               "cname": {
                   required: true,  // 此字段不允许为空
               },
               "note": {
                   required: true,  // 此字段不允许为空
               }
           }
       });
   });
   ```

### 18班级列表（数据层实现）

1. 编写classesMapper.xml

   ```xml
   <!--查询全部数据-->
       <select id="findAll" parameterType="java.util.List" resultMap="classesResultMap">
           SELECT cid,cname,note FROM classes
       </select>
   ```

2. 实现findAll()方法

   ```java
   @Override
       public List<Classes> findAll() throws SQLException {
           return super.getSqlSession().selectList("classesNS.findAll");
       }
   ```

### 19班级列表（服务层实现与JUNIT测试）

1. 定义服务层接口  

   ```java
   /**
    * 实现全部数据列出操作
    * @return 成功返回true，失败返回false；
    * @throws SQLException
    */
   public List<Classes> list() throws SQLException;
   ```

2. 实现服务层接口

   ```java
   @Override
   public List<Classes> list() throws SQLException {
       return this.classesDAO.findAll();
   }
   ```

3. 编写JUNIT测试

   ```java
   @Test
   public void list() throws Exception{
       System.out.println(this.classesService.list());
   }
   ```

### 20班级列表（控制层实现）

1. 第一种方式实现（在页面中使用jstl）

   ```java
   @Autowired
   protected HttpServletRequest request = null;
   ```

   ```java
   @RequestMapping(value = "clsses_list")
   public void list(){
       try {
           super.request.setAttribute("ClassesAll",this.classesService.list());
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   ```

2. 编写json操作类

   ```java
   public static JSONObject convertorObjectToJSON(Object vo){
       JSONObject obj = new JSONObject();
       Field[] field = vo.getClass().getDeclaredFields(); //取得成员名称
       for (int x = 0; x < field.length; x++){
           Field f = field[x];
           String methodName = "get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
           System.out.println(methodName);
           try {
               Method met = vo.getClass().getMethod(methodName);
               if ("Date".equalsIgnoreCase(f.getType().getSimpleName())){
                   Date date = (Date) met.invoke(vo);
                   obj.put(f.getName(),new SimpleDateFormat("yyyy-MM-dd").format(date));
               }else{
                   obj.put(f.getName(),met.invoke(vo));
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return obj;
   }
   ```

3. json列出全部数据

   ```java
   public static JSONObject convertorListToJson(String name, List<?> all) {
       JSONObject obj = new JSONObject();
       JSONArray array = new JSONArray();
       Iterator<?> iter = all.iterator();
       while(iter.hasNext()){
           array.add(convertorObjectToJSON(iter.next()));
       }
       obj.put(name,array);

       return obj;
   }
   ```

4. json处理分页

   ```java
   public static JSONObject convertorListSplitToJson(String name, List<?> all,Integer allRecorders) {
       JSONObject obj = convertorListToJson(name,all);
       obj.put("allRecorders",allRecorders);
       return obj;
   }
   ```

5. 第二种处理方式

   ```java
   @RequestMapping(value = "clsses_list")
   public void list(HttpServletResponse response){
       try {
           super.printObjectToList(response,"allClasses",this.classesService.list());
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   ```

### 21班级列表（页面效果）

1. 编写页面

   ```html
   <table class="table table-bordered" id="classesTable">
       <tr>
           <th>班级编号</th>
           <th>班级名称</th>
           <th>班级简介</th>
       </tr>
   </table>
   ```

2. 编写js代码

   ```javascript
   $(function () {
       loadDate();
   })

   function loadDate() {
       $.post("pages/back/classes/classes_list.action",{},function (obj) {
           $("#classesTable tr:gt(0)").remove();
           for (var x = 0;x <obj.allClasses.length;x++){
               addRow(obj.allClasses[x].cid,obj.allClasses[x].cname,obj.allClasses[x].note);
           }
       },"json");
   }

   function addRow(cid,cname,note) {
       alert(cid);
       var str = "<tr>" +
           "<td>"+cid+"</td>"+
           "<td>"+cname+"</td>"+
           "<td>"+note+"</td>"
           "</tr>";
       $("#classesTable").append($(str));
   }
   ```