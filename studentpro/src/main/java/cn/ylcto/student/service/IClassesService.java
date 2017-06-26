package cn.ylcto.student.service;

import cn.ylcto.student.vo.Classes;

/**
 * Created by kangkang on 2017/6/26.
 */
public interface IClassesService {
    /**
     *  实现班级数据增加操作
     * @param vo 表示要执行增加操作的vo对象
     * @return 成功返回true,失败返回false
     * @throws Exception
     */
    public boolean insert(Classes vo) throws Exception;
}
