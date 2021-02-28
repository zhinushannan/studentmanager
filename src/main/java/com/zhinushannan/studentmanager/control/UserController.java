package com.zhinushannan.studentmanager.control;

import com.zhinushannan.studentmanager.dataobject.ClazzManager;
import com.zhinushannan.studentmanager.dataobject.Student;
import com.zhinushannan.studentmanager.dataobject.Teacher;
import com.zhinushannan.studentmanager.dataobject.UserLoginInfo;
import com.zhinushannan.studentmanager.service.ClazzManagerService;
import com.zhinushannan.studentmanager.service.StudentService;
import com.zhinushannan.studentmanager.service.TeacherService;
import com.zhinushannan.studentmanager.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zhinushannan
 * @date 2020/12/26 12:48
 * @subject
 */
@Controller
public class UserController {

    @Autowired
    private ClazzManagerService clazzManagerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 来自邮箱的验证码
     */
    private Integer code;

    private String id;

    private String type;

    @GetMapping("login")
    public String loginPage(Model model) {
        return "html/login";
    }

    @PostMapping("authenticate")
    public String login(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("type") String type,
                        HttpServletRequest request, HttpServletResponse response, Model model) {
        UserLoginInfo userLoginInfo = new UserLoginInfo(id, password, type);
        HttpSession session = request.getSession();

        String msg = "请检查您填写的信息是否正确！";
        if (type.equals("manager")) {
            ClazzManager manager = this.clazzManagerService.queryManagerById(Long.parseLong(id));
            if (manager != null && manager.getPassword().equals(password)) {
                session.setAttribute("userLoginInfo", userLoginInfo);
            } else {
                model.addAttribute("msg", msg);
                return "html/login";
            }
        } else if (type.equals("student")) {
            Student student = this.studentService.queryStudentById(Long.parseLong(id));
            if (student != null && student.getPassword().equals(password)) {
                session.setAttribute("userLoginInfo", userLoginInfo);
                model.addAttribute("msg", msg);
            } else {
                model.addAttribute("msg", msg);
                return "html/login";
            }
        } else if (type.equals("teacher")) {
            Teacher teacher = this.teacherService.queryTeacherById(Integer.parseInt(id));
            if (teacher != null && teacher.getPassword().equals(password)) {
                session.setAttribute("userLoginInfo", userLoginInfo);
                model.addAttribute("msg", msg);
            } else {
                model.addAttribute("msg", msg);
                return "html/login";
            }
        } else {
            model.addAttribute("msg", msg);
            return "html/login";
        }
        String redirectUrl = "/" + type + "/index.html?" + type + "Id=" + id;
        return "redirect:" + redirectUrl;
    }


    @GetMapping("forget")
    public String forgetPasswordHtml(@RequestParam("id")String id, @RequestParam("type")String type, Model model) {
        this.code = (int)((Math.random()*9 + 1) * 100000);
        this.id = id;
        this.type = type;

        String msg;

        if ("manager".equals(type)) {
            ClazzManager manager = this.clazzManagerService.queryManagerById(Long.parseLong(id));
            if (null != manager) {
                msg = "请重新检查您的账号或身份是否正确";
                model.addAttribute("msg", msg);
                return "html/forget";
            } else {
                String email = manager.getEmail();
                if (null == email) {
                    msg = "您未绑定邮箱，请联系管理员进行密码重置！";
                    model.addAttribute("msg", msg);
                    return "html/forget";
                }
                EmailUtil.sendMail(email, code.toString());
                model.addAttribute("type", type);
                model.addAttribute("id", id);
            }
        } else if ("student".equals(type)) {
            Student student = this.studentService.queryStudentById(Long.parseLong(id));
            if (null != student) {
                msg = "请重新检查您的账号或身份是否正确";
                model.addAttribute("msg", msg);
                return "html/forget";
            } else {
                String email = student.getEmail();
                if (null == email) {
                    msg = "您未绑定邮箱，请联系管理员进行密码重置！";
                    model.addAttribute("msg", msg);
                    return "html/forget";
                }
                EmailUtil.sendMail(email, code.toString());
                model.addAttribute("type", type);
                model.addAttribute("id", id);
            }
        } else if ("teacher".equals(type)) {
            Teacher teacher = this.teacherService.queryTeacherById(Integer.parseInt(id));
            if (null != teacher) {
                msg = "请重新检查您的账号或身份是否正确";
                model.addAttribute("msg", msg);
                return "html/forget";
            } else {
                String email = teacher.getEmail();
                if (null == email) {
                    msg = "您未绑定邮箱，请联系管理员进行密码重置！";
                    model.addAttribute("msg", msg);
                    return "html/forget";
                }
                EmailUtil.sendMail(email, code.toString());
                model.addAttribute("type", type);
                model.addAttribute("id", id);
            }
        }
        return "html/forget";
    }

    @PostMapping("reset")
    public String forgetPassword(@RequestParam Map<String, Object>map, Model model){
        String msg;
        if (!map.get("password").equals(map.get("repeatPassword"))) {
            msg = "两次密码不一致，请重新操作！";
            model.addAttribute("msg", msg);
        }
        if (this.code.equals(map.get("code"))) {
            System.out.println(code);
            System.out.println(map.get("code"));

            if ("student".equals(this.type)) {
                Student student = this.studentService.queryStudentById(Long.parseLong(this.id));
                student.setPassword((String) map.get("password"));
                this.studentService.updateStudentInfo(student);
            } else if("manager".equals(this.type)) {
                ClazzManager manager = this.clazzManagerService.queryManagerById(Long.parseLong(id));
                manager.setPassword((String) map.get("password"));
                this.clazzManagerService.changeManagerPassword(manager);
            } else if("teacher".equals(this.type)){
                Teacher teacher = this.teacherService.queryTeacherById(Integer.parseInt(id));
                teacher.setPassword((String) map.get("password"));
                this.teacherService.updateTeacher(teacher);
            }
            msg = "密码修改成功，请返回登陆！";
            model.addAttribute("msg", msg);
        } else {
            msg = "验证码错误，请重新操作！";
            model.addAttribute("msg", msg);
        }
        return "redirect:/forget";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("userLoginInfo");
        return "redirect:/login";
    }
}
