# SSM学生信息管理系统  



## 章节④   学生模块开发

### 22增加学生（数据层实现）

1. 定义vo类,名称为Student.java

   ```java
   public class Classes implements Serializable {
       private Integer cid;
       private String cname;
       private String note;
       private List<Student> students; //一个班级有多个学生

       public List<Student> getStudents() {
           return students;
       }

       public void setStudents(List<Student> students) {
           this.students = students;
       }

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

       @Override
       public String toString() {
           return "Classes{" +
                   "cid=" + cid +
                   ", cname='" + cname + '\'' +
                   ", note='" + note + '\'' +
                   '}';
       }
   }
   ```

   ```java
   public class Student implements Serializable {
       private String sid;
       private String name;
       private Integer age;
       private Integer sex;
       private String address;
       private Classes classes; //一个学生属于一个班级

       public String getSid() {
           return sid;
       }

       public void setSid(String sid) {
           this.sid = sid;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public Integer getAge() {
           return age;
       }

       public void setAge(Integer age) {
           this.age = age;
       }

       public Integer getSex() {
           return sex;
       }

       public void setSex(Integer sex) {
           this.sex = sex;
       }

       public String getAddress() {
           return address;
       }

       public void setAddress(String address) {
           this.address = address;
       }

       public Classes getClasses() {
           return classes;
       }

       public void setClasses(Classes classes) {
           this.classes = classes;
       }
   }
   ```

2. 编写studentMapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="StudentNS">
      <!-- 实现数据增加操作 -->
       <insert id="doCreate" parameterType="Student">
           INSERT INTO student(sid,name,age,sex,address,cid) VALUES (#{sid},#{name},#{age},#{sex},#{address},#{classes.cid})
       </insert>
   </mapper>
   ```

3. 编写mybatis.cfg.xml文件

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration   
       PUBLIC "-//mybatis.org//DTD Config 3.0//EN"   
       "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
       <!--配置别名-->
      <typeAliases>
           <typeAlias type="cn.ylcto.student.vo.Admin" alias="Admin"/>
           <typeAlias type="cn.ylcto.student.vo.Classes" alias="Classes"/>
           <typeAlias type="cn.ylcto.student.vo.Student" alias="Student"/>
       </typeAliases>
       <mappers>
           <mapper resource="mapper/adminMapper.xml"/>
           <mapper resource="mapper/classesMapper.xml"/>
           <mapper resource="mapper/studentMapper.xml"/>
       </mappers>
   </configuration>
   ```

4. 编写DAO接口

   ```java
   package cn.ylcto.student.dao;

   import cn.ylcto.student.dao.IDAO;
   import cn.ylcto.student.vo.Student;

   /**
    * Created by kangkang on 2017/6/29.
    */
   public interface IStudentDAO extends IDAO<String,Student> {
   }
   ```

5. 编写IStudentDAO的实现类

   ```java
   package cn.ylcto.student.dao.impl;

   import cn.ylcto.student.dao.IStudentDAO;
   import cn.ylcto.student.vo.Student;
   import org.apache.ibatis.session.SqlSessionFactory;
   import org.mybatis.spring.support.SqlSessionDaoSupport;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Repository;

   import java.sql.SQLException;
   import java.util.List;
   import java.util.Set;

   /**
    * Created by kangkang on 2017/6/29.
    */
   @Repository
   public class StudentDAOImpl extends SqlSessionDaoSupport implements IStudentDAO {
       @Autowired
       public StudentDAOImpl(SqlSessionFactory sqlSessionFactory){
           super.setSqlSessionFactory(sqlSessionFactory);
       }

       @Override
       public boolean doCreate(Student vo) throws SQLException {
           return super.getSqlSession().insert("StudentNS.doCreate",vo)>0;
       }
   }
   ```

### 23增加学生（服务层实现与JUNIT测试）

1. 定义服务层接口

   ```java
   package cn.ylcto.student.service;

   import cn.ylcto.student.vo.Student;

   /**
    * Created by kangkang on 2017/6/30.
    */
   public interface IStudentService {
       /**
        * 实现学生数据增加操作
        * @param vo 表示要执行数据增加的vo对象
        * @return 成功返回true，失败返回false
        * @throws Exception
        */
       public boolean insert(Student vo) throws Exception;
   }
   ```

2. 实现服务层接口总的增加学生的方法

   ```java
   package cn.ylcto.student.service.impl;

   import cn.ylcto.student.dao.IStudentDAO;
   import cn.ylcto.student.service.IStudentService;
   import cn.ylcto.student.vo.Student;
   import org.springframework.stereotype.Service;

   import javax.annotation.Resource;

   /**
    * Created by kangkang on 2017/6/30.
    */
   @Service
   public class StudentServiceImpl implements IStudentService {
       @Resource
       private IStudentDAO studentDAO;
       @Override
       public boolean insert(Student vo) throws Exception {
           return this.studentDAO.doCreate(vo);
       }
   }
   ```

3. 编写JUNIT测试

   ```java
   package cn.ylcto.test;

   import cn.ylcto.student.service.IStudentService;
   import cn.ylcto.student.vo.Classes;
   import cn.ylcto.student.vo.Student;
   import junit.framework.TestCase;
   import org.junit.Test;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   ```


   public class StudentServiceTest {
       private static ApplicationContext ctx ;
       private static IStudentService studentService;
    
       static {
           ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
           studentService = ctx.getBean("studentServiceImpl",IStudentService.class);
       }
       @Test
       public void insert()throws Exception{
           Student vo = new Student();
           vo.setSid("YLCTO832");
           vo.setName("张三");
           vo.setAge(23);
           vo.setSex(1); //1表示男  0表示女
           vo.setAddress("XXXX路XXXX号");
           Classes classes = new Classes();
           classes.setCid(1);
           vo.setClasses(classes);
           TestCase.assertTrue(this.studentService.insert(vo));
       }
   }
   ```

### 24增加学生（控制层实现）

1. 新建一个StudentAction.java类

   ```java
   package cn.ylcto.student.action;

   import cn.ylcto.student.service.IStudentService;
   import cn.ylcto.student.vo.Student;
   import cn.ylcto.util.action.DefaultAction;
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.servlet.ModelAndView;

   import javax.annotation.Resource;

   /**
    * Created by kangkang on 2017/6/30.
    */
   @Controller
   @RequestMapping(value="/pages/back/student/*")
   public class StudentAction extends DefaultAction {
       @Resource
       private IStudentService studentService;

       @RequestMapping(value = "student_insert")
       public ModelAndView insert(Student vo) {
           ModelAndView mav = new ModelAndView(super.getResource("pages.forward"));
           try {
               if (this.studentService.insert(vo)){ //表示数据增加成功
                   super.setMsgAndPath(mav,"student.insert.success","student.login.success");
               }else{ //表示数据增加失败
                   super.setMsgAndPath(mav,"student.insert.failure","student.login.failure");
               }

           } catch (Exception e) {
               e.printStackTrace();
           }
           return mav;
       }

       @Override
       public String getText() {
           return "学生";
       }
   }
   ```

2. 编写资源文件

   ```properties
   # {0}增加成功！
   student.insert.success={0}\u589e\u52a0\u6210\u529f\uff01
   # {0}增加失败！
   student.insert.failure={0}\u589e\u52a0\u5931\u8d25\uff01
   ```

   ```properties
   student.login.success=/pages/back/student/student_insert.jsp
   student.login.failure=/pages/back/student/student_insert.jsp
   ```

### 25增加学生（编写页面）

1. 编写表单

   ```html
   <form action="<%=basePath%>pages/back/student/student_insert.action" method="post" class="form-horizontal" id="insertForm">
       <!-- 学生编号 -->
       <div class="form-group">
           <label for="sid" class="control-label col-md-3">学生编号</label>
           <div class=col-md-5>
               <input type="text" class="form-control" name="sid" id="sid" placeholder="请输入学生编号">
           </div>
       </div>

       <!-- 班级编号 -->
       <div class="form-group">
           <label for="sid" class="control-label col-md-3">班级编号</label>
           <div class=col-md-5>
               <select name="classes.cid" id="classes.cid" class="form-control" />
           </div>
       </div>

       <!-- 学生姓名 -->
       <div class="form-group">
           <label for="name" class="control-label col-md-3">学生姓名</label>
           <div class=col-md-5>
               <input type="text" class="form-control" name="name" id="name" placeholder="请输入学生姓名">
           </div>
       </div>

       <!-- 学生年龄 -->
       <div class="form-group">
           <label for="age" class="control-label col-md-3">学生年龄</label>
           <div class=col-md-5>
               <input type="text" class="form-control" name="age" id="age" placeholder="请输入学生年龄">
           </div>
       </div>

       <!-- 学生性别 -->
       <div class="form-group">
           <label class="control-label col-md-3">学生性别</label>
           <div class=col-md-5>
               <label class="radio-inline">
                   <input type="radio" name="sex" id="sex1" value="0" checked>女
               </label>
               <label class="radio-inline">
                   <input type="radio" name="sex" id="sex2" value="1">男
               </label>
           </div>
       </div>

       <!-- 联系地址 -->
       <div class="form-group">
           <label for="address" class="control-label col-md-3">联系地址</label>
           <div class=col-md-5>
               <input type="text" class="form-control" name="address" id="address" placeholder="请输入联系地址">
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

2. 编写验证和异步加载

   ```javascript
   $(function() {  // 在页面加载的时候执行
       loadClasses();
       $("#insertForm").validate({ // 定义验证规则
           debug: true,  // 采用调试模式，表单不会自动提交
           submitHandler: function (form) {    // 当前表单对象
               form.submit(); // 手工提交，如果不需要手工提交，可以在此处进行异步处理
           },
           rules: {   // 为每一个表单编写验证规则
               "sid": {
                   required: true,  // 此字段不允许为空
               },
               "cname": {
                   required: true,  // 此字段不允许为空
               },
               "age": {
                   required: true,  // 此字段不允许为空
               },
               "address": {
                   required: true,  // 此字段不允许为空
               }

           }
       });
   });

   function loadClasses() {
       $.post("pages/back/classes/classes_list.action",{},function (obj) {
           $("#classes\\.cid tr:gt(0)").remove();
           for (var x = 0;x <obj.allClasses.length;x++){
               $("#classes\\.cid").append($("<option value='"+obj.allClasses[x].cid+"'>"+obj.allClasses[x].cname+"</option>"));
           }
       },"json");
   }
   ```

### 26学生列表（数据层实现）

1. 修改classesMapper.xml文件

   ```xml
   <!-- 配置一对多关系 -->
   <collection property="students" column="sid" javaType="java.util.List" ofType="Student"></collection>
   ```

2. 修改studentMapper.xml文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="StudentNS">
       
       <resultMap id="studentResultMap" type="Student">
           <id property="sid" column="sid" />
           <result property="name" column="name" />
           <result property="age" column="age" />
           <result property="sex" column="sex" />
           <result property="address" column="address" />
           <association property="classes" column="cid" javaType="Classes" resultMap="classesNS.classesResultMap"
       </resultMap>

       <!--实现数据增加操作-->
       <insert id="doCreate" parameterType="Student">
           INSERT INTO student(sid,name,age,sex,address,cid)VALUES (#{sid},#{name},#{age},#{sex},#{address},#{classes.cid})
       </insert>

       <!-- 编写分页操作 -->
       <select id="findBySplit" parameterType="java.util.Map" resultMap="studentResultMap">
         SELECT sid,name,age,sex,address FROM student LIMIT ${start},#{lineSize}
       </select>

       <!-- 统计全部数据量 -->
       <select id="getAllCount" parameterType="java.util.Map" resultType="Integer">
           SELECT COUNT(sid) FROM student
       </select>

   </mapper>
   ```

3. 编写接口

   ```java
   package cn.ylcto.student.dao;

   import cn.ylcto.student.dao.IDAO;
   import cn.ylcto.student.vo.Student;

   import java.sql.SQLException;
   import java.util.List;

   /**
    * Created by kangkang on 2017/6/29.
    */
   public interface IStudentDAO extends IDAO<String,Student> {
       /**
        * 实现数据分页操作
        * @param currentPage 表示当前页
        * @param lineSize 表示每页显示记录数
        * @return 成功返回满足条件的数据，失败返回null
        * @throws SQLException
        */
       public List<Student> findAllBySplit(Integer currentPage, Integer lineSize)throws SQLException;

       /**
        * 实现数据量统计操作
        * @return 成功返回数据量，失败返回 0
        * @throws SQLException
        */
       public Integer getAllCount()throws SQLException;
   }
   ```

4. 编写分页实现类

   ```java
   @Override
       public List<Student> findAllBySplit(Integer currentPage, Integer lineSize) throws SQLException {
           Map<String,Object> map = new HashMap<>();
           map.put("start",(currentPage-1)*lineSize); //表示当前页
           map.put("lineSize",lineSize); //表示当前页
           return super.getSqlSession().selectList("StudentNS.findBySplit",map);
       }

       @Override
       public Integer getAllCount() throws SQLException {
           return super.getSqlSession().selectOne("StudentNS.getAllCount");
       }
   ```

### 27学生列表（服务层实现与JUNIT测试）

1. 定义服务层接口

   ```java
   /**
    * 实现数据分页查询操作
    * @param currentPage 表示当前页
    * @param lineSize 表示每页显示记录数
    * @return
    * @throws Exception
    */
   public Map<String,Object> listSplit(int currentPage,int lineSize) throws Exception;
   ```

2. 编写实现类

   ```java
   @Override
   public Map<String, Object> listSplit(int currentPage, int lineSize) throws Exception {
       Map<String,Object> map = new HashMap<>();
       map.put("allStudent",this.studentDAO.findAllBySplit(currentPage,lineSize));
       map.put("studentCount",this.studentDAO.getAllCount());
       return map;
   }
   ```

3. 编写测试类

   ```java
   @Test
   public void list() throws Exception {
       Map<String, Object> map = this.studentService.listSplit(1,2);
       System.out.println(map.get("allStudent"));
       System.out.println(map.get("studentCount"));
       TestCase.assertTrue(map.size() == 2);
   }
   ```

### 28学生列表（编写控制层）

1. 编写列表方法

   ```java
   package cn.ylcto.student.action;

   import cn.ylcto.student.service.IStudentService;
   import cn.ylcto.student.vo.Student;
   import cn.ylcto.util.action.DefaultAction;
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.servlet.ModelAndView;

   import javax.annotation.Resource;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.util.List;
   import java.util.Map;

   /**
    * Created by kangkang on 2017/6/30.
    */
   @Controller
   @RequestMapping(value="/pages/back/student/*")
   public class StudentAction extends DefaultAction {
       @Resource
       private IStudentService studentService;

       @RequestMapping(value = "student_list")
       public void list(HttpServletRequest request, HttpServletResponse response){
           super.handSplit(request,response);
           try {
               Map<String,Object> map = this.studentService.listSplit(super.getCurrentPage(),super.getLineSize());
               List<Student> all = (List<Student>) map.get("allStudent");
               Integer allRecorders = (Integer) map.get("studentCount");
               super.printObjectToListSplit(response,"allStudent",all,allRecorders);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }

   ```

2. 在DefaultAction.java类中增加分页处理

   ```java
   private Integer currentPage = 1; //表示第一页
   private Integer lineSize = 2; //表示每页显示记录数

   public void handSplit(HttpServletRequest request,HttpServletResponse response){
       try {
           this.currentPage = Integer.parseInt(request.getParameter("cp"));
       }catch (Exception e){}
       try {
           this.lineSize = Integer.parseInt(request.getParameter("ls"));
       }catch (Exception e){}
   }

   public Integer getCurrentPage() {
       return currentPage;
   }

   public Integer getLineSize() {
       return lineSize;
   }
   ```

### 29学生列表（编写页面）

1. 编写表单

   ```html
   <table class="table table-bordered table-hover" id="studentTable">
       <tr>
           <th class="text-center"><input type="checkbox" name="" id="" /></th>
           <th class="text-center">学生编号</th>
           <th class="text-center">学生名字</th>
           <th class="text-center">学生年龄</th>
           <th class="text-center">学生性别</th>
           <th class="text-center">联系地址</th>
           <th class="text-center">操作</th>
       </tr>
   </table>
   ```

2. 导入相关js

   ```html
   <script src="jquery/student_list.js"></script>
   <script src="jquery/util.js"></script>
   ```

3. 编写异步加载数据操作

   ```javascript
   $(function () {
       loadData();
   })

   function loadData() { //定义数据读取的操作函数
       $.post("pages/back/student/student_list.action",{},function (obj) {
           $("#studentTable tr:gt(0)").remove();
           for(var x = 0; x < obj.allStudent.length; x++){
               addRow(obj.allStudent[x].sid,obj.allStudent[x].name,obj.allStudent[x].age,obj.allStudent[x].sex,obj.allStudent[x].address);
           }
          createSplitBar(obj.allRecorders);
       },"json");
   }

   function addRow(sid,name,age,sex,address){
       var str = "<tr><td><input type='checkbox' class='text-center' name='sid' id='sid' value='"+sid+"'></td>" +
           "<td class='text-center'>"+sid+"</td>" +
           "<td class='text-center'>"+name+"</td>" +
           "<td class='text-center'>"+age+"</td>" +
           "<td class='text-center'>"+sex+"</td>" +
           "<td class='text-center'>"+address+"</td>" +
           "<td class='text-center'><button type='button' class='btn btn-success btn-sm'>更新</button> </td>" +
           "</tr>";
       $("#studentTable").append($(str));
   }
   ```