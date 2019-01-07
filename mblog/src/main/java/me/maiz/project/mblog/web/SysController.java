package me.maiz.project.mblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysController {

    @RequestMapping("404")
    public String error(){
        return "404";
    }

    @RequestMapping("notLogin")
    public String notLogin(){
        return "notLogin";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }
}
