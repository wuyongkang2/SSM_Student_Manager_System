package cn.ylcto.student.service;

import cn.ylcto.student.vo.Student;

/**
 * Created by kangkang on 2017/6/30.
 */
public interface IStudentService {
    /**
     * 实现学生数据增加操作
     * @param vo 表示要执行数据增加的vo对象
     * @return 成功返回true，失败返回false
     * @throws Exception
     */
    public boolean insert(Student vo) throws Exception;
}
