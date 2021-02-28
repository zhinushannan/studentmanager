package com.zhinushannan.studentmanager.control;

import com.zhinushannan.studentmanager.dataobject.*;
import com.zhinushannan.studentmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/18 15:20
 * @subject
 */
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzService clazzService;

    @GetMapping("teacher/index.html")
    public String index(@RequestParam("teacherId")Integer teacherId, Model model){
        // 获取老师的信息
        Teacher teacher = this.teacherService.queryTeacherById(teacherId);

        // 获取任课老师所带的班级
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);

        model.addAttribute("teacher", teacher);
        model.addAttribute("clazzes", clazzes);

        return "html/teacher/index";
    }

    @GetMapping("teacher/details")
    public String details(@RequestParam("teacherId")Integer teacherId, @RequestParam("clazzId")Integer clazzId, Model model){
        // 获取老师的信息
        Teacher teacher = this.teacherService.queryTeacherById(teacherId);

        // 获取任课老师所带的班级
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);

        // 当前班级
        Clazz clazz = this.clazzService.queryClazzById(clazzId);

        // 获取任课老师课表
        List<TimeTableService.TimeTableDetails> timeTables = this.timeTableService.queryTimeTableDetailsByTeacher(teacherId);

        // 获取该班级的学生
        List<Student> students = this.studentService.queryStudentByClazz(clazzId);

        model.addAttribute("teacher", teacher);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("clazz", clazz);
        model.addAttribute("timeTables", timeTables);
        model.addAttribute("students", students);

        return "html/teacher/indexdetails";
    }

    @GetMapping("teacher/scoreinfo")
    public String scoreInfo(@RequestParam("teacherId")Integer teacherId, @RequestParam("clazzId")Integer clazzId, Model model){

        // 获取老师的信息
        Teacher teacher = this.teacherService.queryTeacherById(teacherId);

        // 获取任课老师所带的班级
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);

        model.addAttribute("teacher", teacher);
        model.addAttribute("clazzes", clazzes);

        return "html/teacher/scoreinfo";
    }

    @GetMapping("teacher/changepwd.html")
    public String changePasswordHtml(@RequestParam("teacherId")Integer teacherId, Model model){
        // 辅导员的个人信息
        Teacher teacher = this.teacherService.queryTeacherById(teacherId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);


        Teacher nullTeacher = new Teacher();

        model.addAttribute("nullTeacher", nullTeacher);
        model.addAttribute("teacher", teacher);
        model.addAttribute("clazzes", clazzes);

        return "html/teacher/changepassword";
    }

    @PostMapping("teacher/password")
    public String changePassword(@Valid Teacher teacher, @RequestParam("teacherId")Integer teacherId, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        Teacher teacher1 = this.teacherService.queryTeacherById(teacherId);
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);
        model.addAttribute("teacher", teacher1);
        model.addAttribute("clazzes",clazzes);

        String oldPassword = teacher1.getPassword();
        redirectAttributes.addFlashAttribute("teacherId",teacherId);
        String msg = "";

        if (result.hasErrors()) {
            return "html/teacher/changepassword";
        } else {
            String[] passwords = teacher.getPassword().split(",");
            if (passwords.length == 0) {
                msg = "密码不能为空！";
                model.addAttribute("msg", msg);
                return "html/teacher/changepassword";
            }
            // 原密码错误
            if (!oldPassword.equals(passwords[0])){
                msg = "原密码错误";
                model.addAttribute("msg", msg);
                return "html/teacher/changepassword";
            }
            // 新旧密码相同
            if (oldPassword.equals(passwords[1])){
                msg = "新密码不能与旧密码相同！";
                model.addAttribute("msg", msg);
                return "html/teacher/changepassword";
            }
            // 两次密码不一致
            if (!passwords[1].equals(passwords[2])) {
                msg = "两次密码不一致";
                model.addAttribute("msg", msg);
                return "html/teacher/changepassword";
            }
            // 修改密码
            teacher1.setPassword(passwords[1]);
            boolean b = this.teacherService.updateTeacher(teacher1);

            // 修改失败
            if (!b) {
                redirectAttributes.addFlashAttribute("errormsg", "修改失败！请稍后重试...");
                return "redirect:/teacher/changepassworderror";
            }
        }
        return "redirect:/teacher/changepasswordsuccess";
    }

    @GetMapping("teacher/changepasswordsuccess")
    public String changePasswordSuccess(@ModelAttribute("teacherId")Integer teacherId, Model model){
        // 辅导员的个人信息
        Teacher teacher = this.teacherService.queryTeacherById(teacherId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);

        model.addAttribute("teacher", teacher);
        model.addAttribute("clazzes", clazzes);

        return "html/teacher/changepasswordsuccess";
    }

    @GetMapping("teacher/changepassworderror")
    public String changePasswordError(@ModelAttribute("teacherId")Integer teacherId,@ModelAttribute("errormsg")String errormsg, Model model){
        // 辅导员的个人信息
        Teacher teacher = this.teacherService.queryTeacherById(teacherId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByTeacherId(teacherId);

        model.addAttribute("teacher", teacher);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("errirmsg", errormsg);

        return "html/teacher/changepassworderror";
    }



}
