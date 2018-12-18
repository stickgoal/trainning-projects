package me.maiz.ittrainning.simplecrawlerboot.web;

import me.maiz.ittrainning.simplecrawlerboot.common.Result;
import me.maiz.ittrainning.simplecrawlerboot.domain.Config;
import me.maiz.ittrainning.simplecrawlerboot.service.ConfigService;
import me.maiz.ittrainning.simplecrawlerboot.web.form.ConfigForm;
import me.maiz.ittrainning.simplecrawlerboot.web.form.ConfigQueryForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigController {
    private static  final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(value="addConfig")
    public Result config(ConfigForm form){
        Config c = new Config();
        BeanUtils.copyProperties(form,c);
        configService.addConfig(c);
        return Result.success();
    }

    @RequestMapping("queryConfig")
    public Result queryConfig(ConfigQueryForm queryForm){
        try {
            List<Config> config = configService.queryConfig(queryForm.getUserId(), queryForm.getPageIdx(), queryForm.getPageSize());
            return Result.success(config);
        }catch (Exception e){
            logger.warn("查询出错",e);
            return Result.generalFail();
        }

    }

}
