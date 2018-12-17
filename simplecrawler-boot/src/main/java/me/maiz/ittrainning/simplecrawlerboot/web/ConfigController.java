package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.common.Result;
import me.maiz.ittrainning.simplecrawlerboot.domain.Config;
import me.maiz.ittrainning.simplecrawlerboot.service.ConfigService;
import me.maiz.ittrainning.simplecrawlerboot.web.form.ConfigForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value="addConfig")
    public Result config(ConfigForm form){
        Config c = new Config();
        BeanUtils.copyProperties(form,c);
        configService.addConfig(c);
        return Result.success();
    }

}
