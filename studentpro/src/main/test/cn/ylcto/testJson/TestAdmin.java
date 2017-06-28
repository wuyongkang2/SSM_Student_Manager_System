package cn.ylcto.testJson;

import cn.ylcto.student.vo.Admin;
import cn.ylcto.util.ObjectToJSON;

import java.util.Date;

/**
 * Created by kangkang on 2017/6/28.
 */
public class TestAdmin {
    public static void main(String[] args) {
        Admin vo = new Admin();
        vo.setEmail("ylcto@163.com");
        vo.setPassword("1111111");
        vo.setLastdate(new Date());
        System.out.println(ObjectToJSON.convertorObjectToJSON(vo));
    }
}
