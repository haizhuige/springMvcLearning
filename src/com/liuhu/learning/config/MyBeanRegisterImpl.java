package com.liuhu.learning.config;

import com.liuhu.learning.annotation.DefAutowired;
import com.liuhu.learning.annotation.她是傻子么;
import com.liuhu.learning.annotation.神奇的注解;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author liuhu-jk
 * @Date 2019/11/10 11:26
 * @Description
 **/
@Component
public class MyBeanRegisterImpl implements BeanDefinitionRegistryPostProcessor {
    private Map<String, Object> map;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry...bean的数量："+registry.getBeanDefinitionCount());
        System.out.println("beanDefinitionRegister的bean名称为："+ Arrays.asList(registry.getBeanDefinitionNames()));
        //RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
       //AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
       // registry.registerBeanDefinition("hello", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...bean的数量："+beanFactory.getBeanDefinitionCount());

    }

   // @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String [] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        if (beanDefinitionNames==null){
            return;
        }
        for (int i = 0; i < applicationContext.getBeanDefinitionNames().length; i++) {
            String beanName = applicationContext.getBeanDefinitionNames()[i];
            Object obj = applicationContext.getBean(beanName);
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                Annotation annotation = method.getAnnotation(她是傻子么.class);
                if (annotation != null) {
                        if (method.getParameterTypes()==null||method.getParameterTypes().length<=0){
                            break;
                        }
                        Class injectClass =method.getParameterTypes()[0];
                        Map map = applicationContext.getBeansOfType(injectClass);
                    if (map==null||map.size()<=0){
                            System.out.println("实现类没有加载在配置中。。。。。。。");
                            break;
                        }
                        try {
                            if (map.size()==1){
                                method.invoke(obj, map.values().iterator().next());
                            }else{
                                String annotationName =((她是傻子么) annotation).name();
                                if (annotationName==null){
                                    System.out.println("注入失败,没有指定实现类。。。。。");
                                }
                                AtomicBoolean flag = new AtomicBoolean(false);
                                map.forEach((key, value) -> {
                                      if (flag.get()){
                                          return;
                                      }
                                      if (key.equals(annotationName)){
                                          try {
                                              method.invoke(obj, value);
                                              flag.set(true);
                                          } catch (Exception e) {
                                              e.printStackTrace();
                                          }
                                      }

                                });
                                if (!flag.get()){
                                    System.out.println("没有找到对应的注册类");
                                    return;
                                }
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }
}
