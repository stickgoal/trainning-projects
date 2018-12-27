package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.common.Result;
import me.maiz.ittrainning.simplecrawlerboot.domain.User;
import me.maiz.ittrainning.simplecrawlerboot.service.LoginService;
import me.maiz.ittrainning.simplecrawlerboot.web.form.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;


    // http://localhost:8080/user/login?username=lucas&password=1234567
    @RequestMapping(value = "login"/*,method = RequestMethod.POST*/)
    public Result login(LoginForm loginFrom){
        try {
            User user = loginService.login(loginFrom);
            return Result.success(user);
        }catch (Exception e){
            logger.warn("登录出错",e);
            return Result.generalFail();
        }
    }

}
