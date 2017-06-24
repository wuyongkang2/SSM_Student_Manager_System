package cn.ylcto.test;

import cn.ylcto.util.MD5Code;

/**
 * Created by kangkang on 2017/6/24.
 */
public class TestMD5Code {
    public static void main(String[] args) {
        String email = "ylcto@163.com";
        String password = "ylcto";
        System.out.println(new MD5Code().getMD5ofStr(password+email));
    }
}
