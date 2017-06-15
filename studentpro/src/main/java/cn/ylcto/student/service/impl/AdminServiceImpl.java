package cn.ylcto.student.service.impl;

import cn.ylcto.student.dao.IAdminDAO;
import cn.ylcto.student.service.IAdminService;
import cn.ylcto.student.vo.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kangkang on 2017/6/15.
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Resource
    private IAdminDAO adminDAO;
    @Override
    public Admin login(Admin vo) throws Exception {
        return this.adminDAO.findLogin(vo);
    }
}
