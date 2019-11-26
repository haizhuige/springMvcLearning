package com.liuhu.learning.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author liuhu-jk
 * @Date 2019/11/11 19:12
 * @Description
 **/
public class MyDynamicProxy implements InvocationHandler {
    private Object object;
    public MyDynamicProxy(Object object){
        this.object =object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("��̬����ִ�з���ǰ��������");
        method.invoke(proxy,args);
        System.out.println("��̬����ִ�з����󡣡�����");
        return null;
    }
}
