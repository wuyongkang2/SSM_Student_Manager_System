package cn.ylcto.student.service.impl;

import cn.ylcto.student.dao.IStudentDAO;
import cn.ylcto.student.service.IStudentService;
import cn.ylcto.student.vo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kangkang on 2017/6/30.
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    private IStudentDAO studentDAO;
    @Override
    public boolean insert(Student vo) throws Exception {
        return this.studentDAO.doCreate(vo);
    }
}
