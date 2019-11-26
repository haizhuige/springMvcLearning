package com.liuhu.learning.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author liuhu-jk
 * @Date 2019/11/15 10:43
 * @Description
 **/
@Component
public class MyListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("收到事件："+event);

    }
}
