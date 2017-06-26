# 2017.06.25 12更新最后一次登录日期（业务层实现）

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