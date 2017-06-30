package cn.ylcto.student.action;

import cn.ylcto.student.service.IStudentService;
import cn.ylcto.student.vo.Student;
import cn.ylcto.util.action.DefaultAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by kangkang on 2017/6/30.
 */
@Controller
@RequestMapping(value="/pages/back/student/*")
public class StudentAction extends DefaultAction {
    @Resource
    private IStudentService studentService;

    @RequestMapping(value = "student_insert")
    public ModelAndView insert(Student vo) {
        ModelAndView mav = new ModelAndView(super.getResource("pages.forward"));
        try {
            if (this.studentService.insert(vo)){ //表示数据增加成功
                super.setMsgAndPath(mav,"student.insert.success","student.login.success");
            }else{ //表示数据增加失败
                super.setMsgAndPath(mav,"student.insert.failure","student.login.failure");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @Override
    public String getText() {
        return "学生";
    }
}
