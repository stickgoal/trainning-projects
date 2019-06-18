package me.maiz.project.mblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class MblogApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","production");
        SpringApplication.run(MblogApplication.class, args);
    }

}

