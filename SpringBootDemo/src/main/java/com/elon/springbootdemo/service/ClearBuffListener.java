package com.elon.springbootdemo.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ClearBuffListener implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("注册订阅消息的听众。");
    }
}
