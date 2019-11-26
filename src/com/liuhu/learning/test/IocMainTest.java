package com.liuhu.learning.test;

import com.liuhu.learning.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author liuhu-jk
 * @Date 2019/11/9 16:02
 * @Description
 **/
public class IocMainTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.getHelloInfo();
    }
}
