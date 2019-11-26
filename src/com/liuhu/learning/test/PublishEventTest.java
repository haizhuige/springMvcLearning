package com.liuhu.learning.test;

import com.liuhu.learning.config.MyEventListenerConfig;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author liuhu-jk
 * @Date 2019/11/15 10:39
 * @Description
 **/
public class PublishEventTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyEventListenerConfig.class);

        applicationContext.publishEvent(new ApplicationEvent(new String("第一个事件。。。。")) {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        applicationContext.close();
    }
}
