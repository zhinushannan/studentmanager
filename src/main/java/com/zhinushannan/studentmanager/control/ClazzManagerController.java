package com.zhinushannan.studentmanager.control;

import com.zhinushannan.studentmanager.dataobject.*;
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

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhinushannan
 * @date 2020/12/16 23:01
 * @subject
 */
@Controller
public class ClazzManagerController {

    @Autowired
    private ClazzManagerService clazzManagerService;

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private StudentService studentService;

    @GetMapping("manager/index.html")
    public String index(@RequestParam("managerId") Long managerId, Model model) {
        // 辅导员的个人信息
        ClazzManager manager = this.clazzManagerService.queryManagerById(managerId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(managerId);

        model.addAttribute("manager", manager);
        model.addAttribute("clazzes", clazzes);

        return "html/manager/index";
    }

    @GetMapping("manager/details")
    public String details(@RequestParam("managerId") Long managerId, @RequestParam("clazzId") Integer clazzId, Model model) {

        // 辅导员的个人信息
        ClazzManager manager = this.clazzManagerService.queryManagerById(managerId);

        // 班级信息
        Clazz clazz = this.clazzService.queryClazzById(clazzId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(managerId);


        // 辅导员下所有的学生
        List<Student> students = this.studentService.queryStudentByClazzManager(managerId).get(clazzId);

        // 辅导员所带班级的课表
        final List<TimeTableService.TimeTableDetails> timeTables = this.timeTableService.queryTimeTableDetailsByClazzId(managerId, clazzId);


        model.addAttribute("manager", manager);
        model.addAttribute("clazz", clazz);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("students", students);
        model.addAttribute("timeTables", timeTables);

        return "html/manager/indexdetails";
    }

    @GetMapping("manager/studentinfo")
    public String studentInfo(@RequestParam("clazzId") Integer clazzId, Model model) {
        List<Student> students = this.studentService.queryStudentByClazz(clazzId);
        ClazzManager manager = this.clazzManagerService.queryManagerByClazzId(clazzId);
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(manager.getId());
        Clazz clazz = this.clazzService.queryClazzById(clazzId);
        model.addAttribute("manager", manager);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("clazz", clazz);
        model.addAttribute("students", students);
        return "html/manager/studentinfo";
    }

    @GetMapping("manager/timetableinfo")
    public String timetableInfo(@RequestParam("clazzId") Integer clazzId, Model model) {
        ClazzManager manager = this.clazzManagerService.queryManagerByClazzId(clazzId);

        List<TimeTableService.TimeTableDetails> timeTableDetails = this.timeTableService.queryTimeTableDetailsByClazzId(manager.getId(), clazzId);

        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(manager.getId());

        Clazz clazz = this.clazzService.queryClazzById(clazzId);

        model.addAttribute("manager", manager);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("clazz", clazz);
        model.addAttribute("timeTableDetails", timeTableDetails);

        return "html/manager/timetableinfo";
    }


    @GetMapping("manager/changepwd.html")
    public String changePasswordHtml(@RequestParam("managerId")Long managerId, Model model){
        // 辅导员的个人信息
        ClazzManager manager = this.clazzManagerService.queryManagerById(managerId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(managerId);


        ClazzManager nullClazzManager = new ClazzManager();

        model.addAttribute("nullClazzManager", nullClazzManager);
        model.addAttribute("manager", manager);
        model.addAttribute("clazzes", clazzes);

        return "html/manager/changepassword";
    }

    @PostMapping("manager/password")
    public String changePassword(@Valid ClazzManager clazzManager, @RequestParam("managerId")Long managerId, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        ClazzManager manager = this.clazzManagerService.queryManagerById(managerId);
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(managerId);
        model.addAttribute("manager", manager);
        model.addAttribute("clazzes",clazzes);

        String oldPassword = manager.getPassword();
        redirectAttributes.addFlashAttribute("managerId",managerId);
        String msg = "";

        if (result.hasErrors()) {
            return "html/manager/changepassword";
        } else {
            String[] passwords = clazzManager.getPassword().split(",");
            if (passwords.length == 0) {
                msg = "密码不能为空！";
                model.addAttribute("msg", msg);
                return "html/manager/changepassword";
            }
            // 原密码错误
            if (!oldPassword.equals(passwords[0])){
                msg = "原密码错误";
                model.addAttribute("msg", msg);
                return "html/manager/changepassword";
            }
            // 新旧密码相同
            if (oldPassword.equals(passwords[1])){
                msg = "新密码不能与旧密码相同！";
                model.addAttribute("msg", msg);
                return "html/manager/changepassword";
            }
            // 两次密码不一致
            if (!passwords[1].equals(passwords[2])) {
                msg = "两次密码不一致";
                model.addAttribute("msg", msg);
                return "html/manager/changepassword";
            }
            // 修改密码
            manager.setPassword(passwords[1]);
            boolean b = this.clazzManagerService.changeManagerPassword(manager);

            // 修改失败
            if (!b) {
                redirectAttributes.addFlashAttribute("errormsg", "修改失败！请稍后重试...");
                return "redirect:/manager/changepassworderror";
            }
        }
        return "redirect:/manager/changepasswordsuccess";
    }

    @GetMapping("manager/changepasswordsuccess")
    public String changePasswordSuccess(@ModelAttribute("managerId")Long managerId, Model model){
        // 辅导员的个人信息
        ClazzManager manager = this.clazzManagerService.queryManagerById(managerId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(managerId);

        model.addAttribute("manager", manager);
        model.addAttribute("clazzes", clazzes);

        return "html/manager/changepasswordsuccess";
    }

    @GetMapping("manager/changepassworderror")
    public String changePasswordError(@ModelAttribute("managerId")Long managerId,@ModelAttribute("errormsg")String errormsg, Model model){
        // 辅导员的个人信息
        ClazzManager manager = this.clazzManagerService.queryManagerById(managerId);

        // 辅导员所带的班级的集合
        List<Clazz> clazzes = this.clazzService.queryClazzByManagerId(managerId);

        model.addAttribute("manager", manager);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("errirmsg", errormsg);

        return "html/manager/changepassworderror";
    }


}
