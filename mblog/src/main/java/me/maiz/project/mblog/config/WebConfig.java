package me.maiz.project.mblog.config;

import me.maiz.project.mblog.component.SecurityInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;

@Configuration
public class WebConfig implements WebMvcConfigurer,InitializingBean {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/write/**","/photo/upload");
    }

    @Autowired
    private ServletContext servletContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("添加属性");
        servletContext.setAttribute("staticServerAddress","localhost:8888");
    }
}
