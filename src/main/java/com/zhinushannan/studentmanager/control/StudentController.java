package com.zhinushannan.studentmanager.control;

import com.zhinushannan.studentmanager.dataobject.Clazz;
import com.zhinushannan.studentmanager.dataobject.ClazzManager;
import com.zhinushannan.studentmanager.dataobject.Student;
import com.zhinushannan.studentmanager.service.ClazzManagerService;
import com.zhinushannan.studentmanager.service.ClazzService;
import com.zhinushannan.studentmanager.service.StudentService;
import com.zhinushannan.studentmanager.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;


/**
 * @author zhinushannan
 * @date 2020/12/18 11:08
 * @subject
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private ClazzManagerService clazzManagerService;

    /**
     * 学生信息管理系统首页，主要内容
     * 1、课表
     * 2、个人信息
     * @param studentId
     * @param model
     * @return
     */
    @GetMapping("student/index.html")
    public String index(@RequestParam("studentId")Long studentId, Model model){

        // 学生个人信息
        Student student = this.studentService.queryStudentById(studentId);

        // 学生所属的班级信息
        Clazz clazz = this.clazzService.queryClazzById(student.getClazzId());

        // 辅导员信息
        ClazzManager manager = this.clazzManagerService.queryManagerByClazzId(clazz.getId());

        // 返回课表信息
        List<String> timeTables = this.timeTableService.queryTimeTableNameByManager(manager.getId()).get(clazz.getId());

        List<TimeTableService.TimeTableDetails> timeTableDetails = this.timeTableService.queryTimeTableDetailsByClazzId(manager.getId(), clazz.getId());

        model.addAttribute("student", student);
        model.addAttribute("clazz", clazz);
        model.addAttribute("manager", manager);
        model.addAttribute("timeTables", timeTables);
        model.addAttribute("timeTableDetails", timeTableDetails);

        return "html/student/index";
    }

    @GetMapping("student/updatepersonalinfo.html")
    public String updatePersonInfo(@RequestParam("studentId")Long studentId, Model model){
        Student nullStudent = new Student();
        Student student = this.studentService.queryStudentById(studentId);
        Clazz clazz = this.clazzService.queryClazzById(student.getClazzId());
        model.addAttribute("nullStudent", nullStudent);
        model.addAttribute("student", student);
        model.addAttribute("clazz", clazz);
        return "html/student/changepersonalinfo";
    }

    @PostMapping("/student/update")
    public String changePersonalInfo(@Valid Student student, @RequestParam("studentId") Long studentId,RedirectAttributes redirectAttributes, BindingResult errors){
        redirectAttributes.addFlashAttribute("studentId", studentId);
        if (errors.hasErrors()) {
            return "html/student/changepersonalinfo";
        } else  {
            Student student1 = this.studentService.queryStudentById(studentId);
            if (!student.getName().equals(student1.getName())){
                student1.setName(student.getName());
            }
            if (!student.getBirthday().equals(student1.getBirthday())){
                student1.setBirthday(student.getBirthday());
            }
            if (!student.getMobile().equals(student1.getMobile())){
                student1.setMobile(student.getMobile());
            }
            if (!student.getEmail().equals(student1.getEmail())){
                student1.setEmail(student.getEmail());
            }
            if (!student.getHomeAddress().equals(student1.getHomeAddress())){
                student1.setHomeAddress(student.getHomeAddress());
            }
            this.studentService.updateStudentInfo(student1);
        }
        return "redirect:/student/personalinfo.html";
    }

    @GetMapping("student/personalinfo.html")
    public String personalinfo(@ModelAttribute("studentId")Long studentId, Model model){
        Student student = this.studentService.queryStudentById(studentId);
        Clazz clazz = this.clazzService.queryClazzById(student.getClazzId());
        model.addAttribute("student", student);
        model.addAttribute("clazz",clazz);

        return "html/student/personalinfo";
    }

    @GetMapping("student/changepwd.html")
    public String changePasswordHtml(@RequestParam("studentId")Long studentId, Model model){
        // 学生的个人信息
        Student student = this.studentService.queryStudentById(studentId);

        // 学生所在的班级
        Clazz clazz = this.clazzService.queryClazzById(student.getClazzId());

        Student nullStudent = new Student();

        model.addAttribute("nullStudent", nullStudent);
        model.addAttribute("student", student);
        model.addAttribute("clazz", clazz);
        return "html/student/changepassword";
    }

    @PostMapping("student/password")
    public String changePassword(@Valid Student student, @RequestParam("studentId")Long studentId, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        Student student1 = this.studentService.queryStudentById(studentId);
        Clazz clazz = this.clazzService.queryClazzById(student1.getClazzId());
        model.addAttribute("student", student1);
        model.addAttribute("clazz", clazz);

        String oldPassword = student1.getPassword();
        redirectAttributes.addFlashAttribute("studentId",studentId);
        String msg;

        if (result.hasErrors()) {
            return "html/student/changepassword";
        } else {
            String[] passwords = student.getPassword().split(",");
            // 密码为空
            if (passwords.length == 0) {
                msg = "密码不能为空！";
                model.addAttribute("msg", msg);
                return "html/student/changepassword";
            }
            // 原密码错误
            if (!oldPassword.equals(passwords[0])){
                msg = "原密码错误";
                model.addAttribute("msg", msg);
                return "html/student/changepassword";
            }
            // 新旧密码相同
            if (oldPassword.equals(passwords[1])){
                msg = "新密码不能与旧密码相同！";
                model.addAttribute("msg", msg);
                return "html/student/changepassword";
            }
            // 两次密码不一致
            if (!passwords[1].equals(passwords[2])) {
                msg = "两次密码不一致";
                model.addAttribute("msg", msg);
                return "html/student/changepassword";
            }
            // 修改密码
            student1.setPassword(passwords[1]);
            boolean b = this.studentService.changeStudentPassword(student1);
            // 修改失败
            if (!b) {
                redirectAttributes.addFlashAttribute("errormsg", "修改失败！请稍后重试...");
                return "redirect:/student/changepassworderror";
            }
        }
        return "redirect:/student/changepasswordsuccess";
    }

    @GetMapping("student/changepasswordsuccess")
    public String changePasswordSuccess(@ModelAttribute("studentId")Long studentId, Model model){
        // 学生的个人信息
        Student student = this.studentService.queryStudentById(studentId);

        // 学生所在的班级
        Clazz clazz = this.clazzService.queryClazzById(student.getClazzId());

        model.addAttribute("student", student);
        model.addAttribute("clazz", clazz);

        return "html/student/changepasswordsuccess";
    }

    @GetMapping("student/changepassworderror")
    public String changePasswordError(@ModelAttribute("studentId")Long studentId,@ModelAttribute("errormsg")String errormsg, Model model){
        // 学生的个人信息
        Student student = this.studentService.queryStudentById(studentId);

        // 学生所在的班级
        Clazz clazz = this.clazzService.queryClazzById(student.getClazzId());

        model.addAttribute("student", student);
        model.addAttribute("clazz", clazz);
        model.addAttribute("errirmsg", errormsg);

        return "html/student/changepassworderror";
    }


}
