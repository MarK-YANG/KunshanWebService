package cn.edu.tongji.sse.spring.controller;

import cn.edu.tongji.sse.spring.dao.WifiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Set;

/**
 * Created by mark on 12/4/16.
 */

@Controller
public class HelloController {

    @Autowired
    private WifiDAO _wifiDAO;

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name", "Mark Yang");
        return "welcome";
    }

    @RequestMapping(value="/wifi")
    public @ResponseBody Set<String> listWifi(){
        Set<String> setWifi = _wifiDAO.set();
        return setWifi;
    }
}
