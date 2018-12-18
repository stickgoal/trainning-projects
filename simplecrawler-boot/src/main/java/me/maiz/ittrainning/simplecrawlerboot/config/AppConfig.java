package me.maiz.ittrainning.simplecrawlerboot.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public AsyncEventBus asyncEventBus(){
        return new AsyncEventBus(Executors.newFixedThreadPool(20));
    }

}
