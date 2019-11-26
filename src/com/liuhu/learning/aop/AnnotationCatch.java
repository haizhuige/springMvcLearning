package com.liuhu.learning.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @Author liuhu-jk
 * @Date 2019/11/9 21:04
 * @Description
 **/
@Aspect
@Component
public class AnnotationCatch  implements ImportBeanDefinitionRegistrar {

    Class claz =null;
    String beanName =null;

    @Pointcut("@annotation(com.liuhu.learning.annotation.DefAutowired)")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void beforeClass(JoinPoint joinPoint){
        System.out.println("执行之前。。。。"+joinPoint.getSignature().getName());
    }

    @Around("pointCut()")
    public Object aroundClass(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环形通知"+joinPoint.getSignature().getName());
        claz = joinPoint.getTarget().getClass();
        beanName = joinPoint.getSignature().getName();
        return joinPoint.proceed();
    }




    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //指定Bean定义信息；（Bean的类型，Bean。。。）
        System.out.println("注册的类为"+claz);
        RootBeanDefinition beanDefinition = new RootBeanDefinition(claz);
        System.out.println("注册的类名为"+beanName);
        //注册一个Bean，指定bean名
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
