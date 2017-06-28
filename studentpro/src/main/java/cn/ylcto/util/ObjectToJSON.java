package cn.ylcto.util;

import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kangkang on 2017/6/28.
 */
public class ObjectToJSON {
    public static JSONObject convertorObjectToJSON(Object vo){
        JSONObject obj = new JSONObject();
        Field[] field = vo.getClass().getDeclaredFields(); //取得成员名称
        for (int x = 0; x < field.length; x++){
            Field f = field[x];
            String methodName = "get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
            System.out.println(methodName);
            try {
                Method met = vo.getClass().getMethod(methodName);
                if ("Date".equalsIgnoreCase(f.getType().getSimpleName())){
                    Date date = (Date) met.invoke(vo);
                    obj.put(f.getName(),new SimpleDateFormat("yyyy-MM-dd").format(date));
                }else{
                    obj.put(f.getName(),met.invoke(vo));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
