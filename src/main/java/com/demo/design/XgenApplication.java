package com.demo.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XgenApplication {
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(XgenApplication.class);
        application.run(args);
    }
}
