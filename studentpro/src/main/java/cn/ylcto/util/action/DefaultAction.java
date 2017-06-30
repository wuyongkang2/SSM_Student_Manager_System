package cn.ylcto.util.action;

import cn.ylcto.util.ObjectToJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by kangkang on 2017/6/24.
 */
public abstract class DefaultAction {
    @Resource
    private MessageSource messageSource;
    protected HttpServletRequest request = null;

    private Integer currentPage = 1; //表示第一页
    private Integer lineSize = 2; //表示每页显示记录数

    public void handSplit(HttpServletRequest request,HttpServletResponse response){
        try {
            this.currentPage = Integer.parseInt(request.getParameter("cp"));
        }catch (Exception e){}
        try {
            this.lineSize = Integer.parseInt(request.getParameter("ls"));
        }catch (Exception e){}
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getLineSize() {
        return lineSize;
    }

    /**
     * 可以根据key取得资源的内容
     * @param key 表示要读取的key
     * @param param 表示设置的参数
     * @return
     */
    public String getResource(String key,String ...param){
        return this.messageSource.getMessage(key,param, Locale.getDefault());
    }

    /**
     * 设置提示信息与跳转路径
     * @param mav
     * @param msg
     * @param path
     */
    public void setMsgAndPath(ModelAndView mav,String msg,String path){
        if (mav != null){
             if (this.getText() != null){
                 String[] result = this.getText().split("\\|");
                 mav.addObject("msg",this.messageSource.getMessage(msg,result,Locale.getDefault()));
             }else{
                 mav.addObject("msg",this.messageSource.getMessage(msg,null,Locale.getDefault()));
             }
            mav.addObject("path",this.messageSource.getMessage(path,null,Locale.getDefault()));
        }
    }

    public void printObjectToJson(HttpServletResponse response,Object vo){
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(ObjectToJSON.convertorObjectToJSON(vo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将list集合数据转换为Json
     * @param response
     * @param name
     * @param all
     */
    public void printObjectToList(HttpServletResponse response,String name, List<?> all){
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(ObjectToJSON.convertorListToJson(name,all));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将集合数据转换为Json，并实现分页处理
     * @param response
     * @param name
     * @param all
     * @param allRecorders
     */
    public void printObjectToListSplit(HttpServletResponse response,String name, List<?> all,Integer allRecorders){
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(ObjectToJSON.convertorListSplitToJson(name,all,allRecorders));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public abstract String getText();
}
