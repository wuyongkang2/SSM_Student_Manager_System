package cn.ylcto.student.service.impl;

import cn.ylcto.student.dao.IClassesDAO;
import cn.ylcto.student.service.IClassesService;
import cn.ylcto.student.vo.Classes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kangkang on 2017/6/26.
 */
@Service
public class ClassesServiceImpl implements IClassesService {
    @Resource
    private IClassesDAO classesDAO;
    @Override
    public boolean insert(Classes vo) throws Exception {
        if (this.classesDAO.findByCname(vo.getCname()) == null){
            return this.classesDAO.doCreate(vo);
        }
        return false;
    }

    @Override
    public List<Classes> list() throws SQLException {
        return this.classesDAO.findAll();
    }
}
