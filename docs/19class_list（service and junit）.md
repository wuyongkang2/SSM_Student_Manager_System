# 2017.06.27 18班级列表（数据层实现）  

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