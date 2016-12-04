package cn.edu.tongji.sse.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by mark on 12/4/16.
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name", "Mark Yang");

        return "welcome";
    }
}
