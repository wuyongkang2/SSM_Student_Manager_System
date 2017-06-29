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