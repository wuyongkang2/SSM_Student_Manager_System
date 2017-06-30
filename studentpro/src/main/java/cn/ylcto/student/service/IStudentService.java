package cn.ylcto.student.service;

import cn.ylcto.student.vo.Student;

import java.util.Map;

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

    /**
     * 实现数据分页查询操作
     * @param currentPage 表示当前页
     * @param lineSize 表示每页显示记录数
     * @return
     * @throws Exception
     */
    public Map<String,Object> listSplit(int currentPage,int lineSize) throws Exception;

    /**
     * 实现数据更新操作方法
     * @param vo 表示要执行更新操作的数据
     * @return 成功返回true,失败返回false
     * @throws Exception
     */
    public boolean update(Student vo) throws Exception;
}
