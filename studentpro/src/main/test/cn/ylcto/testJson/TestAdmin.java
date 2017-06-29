package cn.ylcto.testJson;

import cn.ylcto.student.vo.Admin;
import cn.ylcto.util.ObjectToJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kangkang on 2017/6/28.
 */
public class TestAdmin {
    public static void main(String[] args) {
        List<Admin> all = new ArrayList<Admin>();
        for (int x = 0; x < 5; x++){
            Admin vo = new Admin();
            vo.setEmail("ylcto@163.com");
            vo.setPassword("1111111");
            vo.setLastdate(new Date());
            all.add(vo);
        }

        System.out.println(ObjectToJSON.convertorListToJson("allAdmin",all));
    }
}
