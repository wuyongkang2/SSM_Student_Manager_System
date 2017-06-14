package cn.ylcto.student.vo;

import java.io.Serializable;

/**
 * Created by kangkang on 2017/6/11.
 */
public class Admin implements Serializable  {
    private String email;
    private String password;
    private String lastdate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }
}
