package com.liuhu.learning.config;

import com.liuhu.learning.annotation.DefAutowired;
import com.liuhu.learning.aop.AnnotationCatch;
import com.liuhu.learning.service.impl.Accept2ServiceImpl;
import com.liuhu.learning.service.impl.AcceptServiceImpl;
import com.liuhu.learning.service.impl.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author liuhu-jk
 * @Date 2019/11/9 17:50
 * @Description
 **/
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "com.liuhu.learning")
public class MyIocConfig {

    @Bean
    public HelloServiceImpl getHello(){
        return new HelloServiceImpl();
    }
    @Bean
    public Accept2ServiceImpl getAccept2(){
        return new Accept2ServiceImpl();
    }
   //@Bean
    public AcceptServiceImpl getAccept1(){
        return new AcceptServiceImpl();
    }

    @Bean
    public AnnotationCatch getAnnotationCatch(){
        return new AnnotationCatch();
    }


}
