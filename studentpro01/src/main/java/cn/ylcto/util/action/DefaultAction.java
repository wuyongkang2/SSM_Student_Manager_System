package cn.ylcto.util.action;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Locale;

public abstract class DefaultAction {
    @Resource
    private MessageSource messageSource;

    /**
     * 可以根据Key取得资源的内容
     * @param key 表示要读取key
     * @param param 表示设置的参数
     * @return
     */
    public String getResource(String key,String ...param){
        return this.messageSource.getMessage(key,param, Locale.getDefault());
    }

    /**
     * 设置提示信息与跳转路径，通过资源文件读取的
     * @param mav
     * @param msg
     * @param path
     */
    public void setMsgAndPath(ModelAndView mav,String msg,String path){
        if (mav != null){
            if (this.getText() != null){
                String [] result = this.getText().split("\\|");
                mav.addObject("msg",this.messageSource.getMessage(msg,result,Locale.getDefault()));
            }else{
                mav.addObject("msg",this.messageSource.getMessage(msg,null,Locale.getDefault()));
            }
            mav.addObject("path",this.messageSource.getMessage(path,null,Locale.getDefault()));

        }
    }

    public abstract String getText();
}
