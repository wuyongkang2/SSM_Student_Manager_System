package cn.ylcto.student.vo;

import java.io.Serializable;

/**
 * Created by kangkang on 2017/6/29.
 */
public class Student implements Serializable {
    private String sid;
    private String name;
    private Integer age;
    private Integer sex;
    private String address;
    private Classes classes; //一个学生属于一个班级

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
