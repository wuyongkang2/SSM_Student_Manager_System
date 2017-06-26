# 2017.06.26 15班级增加（完善业务）

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