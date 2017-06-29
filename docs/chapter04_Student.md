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