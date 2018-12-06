package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.common.Result;
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
@RequestMapping("user")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;



    @RequestMapping(value = "login"/*,method = RequestMethod.POST*/)
    public Result login(LoginForm loginFrom){
        try {
            boolean success = loginService.login(loginFrom);
            return success?Result.success():Result.fail("1001","用户名不存在或密码不匹配");
        }catch (Exception e){
            logger.warn("登录出错",e);
            return Result.generalFail();
        }
    }

}
