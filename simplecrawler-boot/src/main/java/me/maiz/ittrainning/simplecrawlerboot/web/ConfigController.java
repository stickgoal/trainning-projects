package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class ConfigController {

    @RequestMapping(value="config")
    public Result config(){
        return Result.success();
    }

}
