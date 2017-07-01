package cn.ylcto.student.action;

import cn.ylcto.student.service.IStudentService;
import cn.ylcto.student.vo.Student;
import cn.ylcto.util.action.DefaultAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kangkang on 2017/6/30.
 */
@Controller
@RequestMapping(value="/pages/back/student/*")
public class StudentAction extends DefaultAction {
    @Resource
    private IStudentService studentService;

    @RequestMapping(value = "student_delete")
    public  void delete(HttpServletResponse response,HttpServletRequest request) throws Exception {
        try {
            String result [] = request.getParameter("ids").split("\\|");
            List<String> all = new ArrayList<>();
            for (int x = 0; x < result.length; x++){
                all.add(result[x]);
            }
            if (all.size() > 0){
                super.print(response,this.studentService.delete(all));
            }else{
                super.print(response,"false"); //删除数据不成功返回false
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping(value="student_update")
    public void update(HttpServletResponse response,Student vo){
        try {
            super.print(response,this.studentService.update(vo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "student_list")
    public void list(HttpServletRequest request, HttpServletResponse response){
        super.handSplit(request,response);
        try {
            Map<String,Object> map = this.studentService.listSplit(super.getCurrentPage(),super.getLineSize());
            List<Student> all = (List<Student>) map.get("allStudent");
            Integer allRecorders = (Integer) map.get("studentCount");
            super.printObjectToListSplit(response,"allStudent",all,allRecorders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
