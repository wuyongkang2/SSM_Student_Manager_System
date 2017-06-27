# 2017.06.27 18班级列表（数据层实现）  

1. 编写classesMapper.xml

   ```xml
   <!--查询全部数据-->
       <select id="findAll" parameterType="java.util.List" resultMap="classesResultMap">
           SELECT cid,cname,note FROM classes
       </select>
   ```

