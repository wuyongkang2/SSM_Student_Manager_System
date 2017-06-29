package cn.ylcto.student.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kangkang on 2017/6/25.
 */
public class Classes implements Serializable {
    private Integer cid;
    private String cname;
    private String note;
    private List<Student> students; //一个班级有多个学生

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
