package cn.ylcto.student.service;

import cn.ylcto.student.vo.Admin;

/**
 * Created by kangkang on 2017/6/15.
 */
public interface IAdminService {
    /**
     *  实现管理员登录
     * @param vo 包含要执行登录检查的字段 Email password
     * @return
     * @throws Exception
     */
    public Admin login(Admin vo) throws Exception;
}
