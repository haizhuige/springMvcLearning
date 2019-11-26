package com.liuhu.learning.config;

import com.liuhu.learning.annotation.DefAutowired;
import com.liuhu.learning.annotation.她是傻子么;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author liuhu-jk
 * @Date 2019/11/11 19:14
 * @Description   自定义注入可以模拟set注入和field注入  @setter注入原理：通过set方法 method.invoke(调用对象，被调用对象)
 * field注入原理：通过field初始化完成  field.set(调用对象，被调用对象)
 *
 **/
@Component
public class MyApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        if (beanDefinitionNames == null) {
            return;
        }
        for (int i = 0; i < applicationContext.getBeanDefinitionNames().length; i++) {
            String beanName = applicationContext.getBeanDefinitionNames()[i];
            Object obj = applicationContext.getBean(beanName);
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation annotation = field.getAnnotation(她是傻子么.class);
                if (annotation != null) {
                    Class clazz = field.getType();
                    Map clazzImplMap = applicationContext.getBeansOfType(clazz);
                    if (clazzImplMap == null || clazzImplMap.size() <= 0) {
                        System.out.println("实现类没有加载在配置中。。。。。。。");
                        continue;
                    }
                    if (clazzImplMap.size() == 1) {
                        this.wrapperProxy(field, obj, clazzImplMap.values().iterator().next());
                    } else {
                        String annotationName = ((她是傻子么) annotation).name();
                        if (annotationName == null) {
                            System.out.println("注入失败,没有指定实现类。。。。。");
                        }
                        AtomicBoolean flag = new AtomicBoolean(false);
                        clazzImplMap.forEach((key, value) -> {
                            if (flag.get()) {
                                return;
                            }
                            if (key.equals(annotationName)) {
                                try {
                                    this.wrapperProxy(field, obj, value);
                                    flag.set(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                        if (!flag.get()) {
                            System.out.println("没有找到对应的注册类");
                            continue;
                        }
                    }
                }
            }
        }
    }

    private void wrapperProxy(Field field, Object objImpl, Object obj) {
        try {
            field.setAccessible(true);
            field.set(objImpl, obj);
            System.out.println("注入Field初始化成功。。。。。。");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
