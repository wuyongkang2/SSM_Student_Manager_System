package cn.ylcto.util.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by kangkang on 2017/6/24.
 */
public abstract class DefaultAction {
    @Resource
    private MessageSource messageSource;

    @Autowired
    protected HttpServletRequest request = null;

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

    public abstract String getText();
}
