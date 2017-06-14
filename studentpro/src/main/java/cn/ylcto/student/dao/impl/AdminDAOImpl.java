package cn.ylcto.student.dao.impl;

import cn.ylcto.student.dao.IAdminDAO;
import cn.ylcto.student.vo.Admin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by kangkang on 2017/6/14.
 */
@Repository
public class AdminDAOImpl extends SqlSessionDaoSupport implements IAdminDAO {
    @Autowired
    private AdminDAOImpl(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public Admin findLogin(Admin vo) throws SQLException {
        return super.getSqlSession().selectOne("AdminNS findLogin",vo);
    }

    public boolean doCreate(Object vo) throws SQLException {
        return false;
    }

    public boolean doUpdate(Object vo) throws SQLException {
        return false;
    }

    public Object findById(Object id) throws SQLException {
        return null;
    }

    public List findAll() throws SQLException {
        return null;
    }

    public List findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }

    public boolean doRemove(Set ids) throws SQLException {
        return false;
    }
}
