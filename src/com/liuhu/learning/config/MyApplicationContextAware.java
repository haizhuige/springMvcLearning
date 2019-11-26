package com.liuhu.learning.config;

import com.liuhu.learning.annotation.DefAutowired;
import com.liuhu.learning.annotation.����ɵ��ô;
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
 * @Description   �Զ���ע�����ģ��setע���fieldע��  @setterע��ԭ��ͨ��set���� method.invoke(���ö��󣬱����ö���)
 * fieldע��ԭ��ͨ��field��ʼ�����  field.set(���ö��󣬱����ö���)
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
                Annotation annotation = field.getAnnotation(����ɵ��ô.class);
                if (annotation != null) {
                    Class clazz = field.getType();
                    Map clazzImplMap = applicationContext.getBeansOfType(clazz);
                    if (clazzImplMap == null || clazzImplMap.size() <= 0) {
                        System.out.println("ʵ����û�м����������С�������������");
                        continue;
                    }
                    if (clazzImplMap.size() == 1) {
                        this.wrapperProxy(field, obj, clazzImplMap.values().iterator().next());
                    } else {
                        String annotationName = ((����ɵ��ô) annotation).name();
                        if (annotationName == null) {
                            System.out.println("ע��ʧ��,û��ָ��ʵ���ࡣ��������");
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
                            System.out.println("û���ҵ���Ӧ��ע����");
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
            System.out.println("ע��Field��ʼ���ɹ�������������");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
