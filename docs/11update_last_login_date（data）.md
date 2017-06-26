# 2017.06.25 11更新最后一次登录日期（数据层实现）
实现最后一次登录日期更新

1. 修改遗留问题  
	```java
	request.getSession().setAttribute("email",vo.getEmail());
	request.getSession().setAttribute("lastdate",new SimpleDateFormat("yyyy-MM-dd").format(vo.getLastdate())); //取得最后一次登录日期操作
	```
1. 编写adminMapper.xml  
	```java
	@Override
	    public boolean doUpdate(Admin vo) throws SQLException {
	        return super.getSqlSession().update("AdminNS.doUpdateLastDate",vo) > 0;
	    }
	```
1. 编写AdminDAOImpl.java
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