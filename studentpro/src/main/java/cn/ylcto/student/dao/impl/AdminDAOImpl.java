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
@Repository
public class AdminDAOImpl extends SqlSessionDaoSupport implements IAdminDAO {
    @Autowired
    public AdminDAOImpl(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public Admin findLogin(Admin vo) throws SQLException {
        return super.getSqlSession().selectOne("AdminNS.findLogin",vo);
    }

    @Override
    public boolean doCreate(Admin vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Admin vo) throws SQLException {
        return super.getSqlSession().update("AdminNS.doUpdateLastDate",vo) > 0;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Admin findById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Admin> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
