# 2017.06.25 11更新最后一次登录日期（数据层实现）
实现最后一次登录日期更新

1. 修改遗留问题  
	```java
	request.getSession().setAttribute("email",vo.getEmail());
	request.getSession().setAttribute("lastdate",new SimpleDateFormat("yyyy-MM-dd").format(vo.getLastdate())); //取得最后一次登录日期操作
	```
1. 编写adminMapper.xml  
	```xml
	    <!--更新最后一次登录日期-->
	    <update id="doUpdateLastDate" parameterType="Admin">
	        UPDATE admin SET lastdate=#{lastdate} WHERE email=#{email}
	    </update>
	```
1. 编写AdminDAOImpl.java
	```java
	@Override
	    public boolean doUpdate(Admin vo) throws SQLException {
	        return super.getSqlSession().update("AdminNS.doUpdateLastDate",vo) > 0;
	    }
	```