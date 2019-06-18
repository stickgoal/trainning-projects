package me.maiz.project.mblog.config;

import com.samskivert.mustache.Mustache;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MustacheConfig implements InitializingBean,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("启动时修改Mustache配置...");
        Mustache.Compiler compiler = applicationContext.getBean(Mustache.Compiler.class);
        log.info("{}",compiler);
        compiler.withFormatter((value)-> {
            log.info("启动时修改Mustache配置...");

            String result=null;
            if(value instanceof Date){
                result = DateFormatUtils.format((Date)value,"yyyy-mm-dd");
            }else{
                result=String.valueOf(value);
            }
            return result;
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
