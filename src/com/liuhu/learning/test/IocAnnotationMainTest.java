package com.liuhu.learning.test;

import com.liuhu.learning.config.MyIocConfig;
import com.liuhu.learning.service.HelloService;
import com.liuhu.learning.service.impl.HelloServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author liuhu-jk
 * @Date 2019/11/9 16:02
 * @Description
 **/
public class IocAnnotationMainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyIocConfig.class);
        System.out.println();
       // context.start();
        HelloService helloService = (HelloService) context.getBean("getHello");
        helloService.getHelloInfo();

    }
}
