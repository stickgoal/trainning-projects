package me.maiz.project.mblog.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 增加全局的ModelAttribute
 */
@ControllerAdvice
public class ModelAttributeAdvice {


    @Value("${staticcontent.server.address}")
    private String staticServerAddress;

    @ModelAttribute("staticServerAddress")
    public String staticServerAddr(){
        System.out.println("add========");
        return staticServerAddress;
    }
}
