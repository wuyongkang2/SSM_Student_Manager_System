package cn.ylcto.student.dao;

import cn.ylcto.student.vo.Classes;

import java.sql.SQLException;

/**
 * Created by kangkang on 2017/6/25.
 */
public interface IClassesDAO extends IDAO<Integer,Classes> {
    /**
     * 实现班级名称查询操作
     * @param cname 表示要执行查询的班级名称
     * @return
     * @throws SQLException
     */
    public Classes findByCname(String cname) throws SQLException;
}
