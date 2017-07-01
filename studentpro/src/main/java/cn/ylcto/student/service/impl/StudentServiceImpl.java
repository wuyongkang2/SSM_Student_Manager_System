package cn.ylcto.student.service.impl;

import cn.ylcto.student.dao.IStudentDAO;
import cn.ylcto.student.service.IStudentService;
import cn.ylcto.student.vo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> listSplit(int currentPage, int lineSize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("allStudent",this.studentDAO.findAllBySplit(currentPage,lineSize));
        map.put("studentCount",this.studentDAO.getAllCount());
        return map;
    }

    @Override
    public boolean update(Student vo) throws Exception {
        return this.studentDAO.doUpdate(vo);
    }

    @Override
    public boolean delete(List<String> ids) throws Exception {
        return this.studentDAO.doRemoveBatch(ids);
    }
}
