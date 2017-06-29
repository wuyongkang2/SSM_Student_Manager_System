package cn.ylcto.student.dao.impl;

import cn.ylcto.student.dao.IStudentDAO;
import cn.ylcto.student.vo.Student;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by kangkang on 2017/6/29.
 */
@Repository
public class StudentDAOImpl extends SqlSessionDaoSupport implements IStudentDAO {
    @Autowired
    public StudentDAOImpl(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public boolean doCreate(Student vo) throws SQLException {
        return super.getSqlSession().insert("StudentNS.doCreate",vo)>0;
    }

    @Override
    public boolean doUpdate(Student vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Student findById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Student> findAllBySplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
