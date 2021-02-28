package com.zhinushannan.studentmanager.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhinushannan
 * @date 2020/12/26 16:56
 * @subject
 */
@Controller
public class AboutUsController {

    @GetMapping("about/zhinushannan.html")
    public String aboutUs(){
        return "html/aboutus";
    }

}
