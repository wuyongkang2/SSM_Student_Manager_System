package cn.ylcto.util.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kangkang on 2017/7/2.
 */
public class Validator {
    /**
     * 验证操作
     * @param request
     * @param rule
     * @return
     */
    public boolean validate(HttpServletRequest request,String rule){
        boolean flag = true;
        String result[] = rule.split("\\|");
        for (int x = 0; x < result.length; x++){
            String temp[] = result[x].split(":");
            String value = request.getParameter(temp[0]);
            if (value != null){
                if ("string".equals(temp[1])){
                    flag = this.validateString(value);
                }else if ("number".equals(temp[1])){
                    flag = this.validateNumber(value);
                }else if ("date".equals(temp[1])){
                    flag = this.validateDate(value);
                }
                if (flag == flag){
                    request.setAttribute(temp[0],"ruleError");
                }
            }else{
                flag = false;
                request.setAttribute(temp[0],"valueError");
            }
        }
        return flag;
    }
    public boolean validateString(String str){
        if (str == null || "".equals(str)){
            return false;
        }
        return true;
    }
    public boolean validateNumber(String str){
        if (this.validateString(str)){ //表示有内容存在
            return str.matches("\\d+(\\.\\d+)?");
        }
        return false;
    }
    public boolean validateDate(String str){
        if (this.validateString(str)){
            if (str.matches("\\d{4}-\\d{2}-\\d{2}")){
                return true;
            }else{
                return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
            }
        }
        return false;
    }
}
