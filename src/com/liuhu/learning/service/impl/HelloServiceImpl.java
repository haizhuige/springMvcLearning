package com.liuhu.learning.service.impl;

import com.liuhu.learning.annotation.DefAutowired;
import com.liuhu.learning.annotation.����ɵ��ô;
import com.liuhu.learning.service.AcceptService;
import com.liuhu.learning.service.HelloService;
import com.liuhu.learning.service.NoImplClass;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author liuhu-jk
 * @Date 2019/11/11 17:47
 * @Description
 **/
public class HelloServiceImpl implements HelloService {

    @Autowired
    AcceptService acceptService;
    @Override
    public String getHelloInfo() {
        System.out.println("���ý��շ�����ϢΪ��"+acceptService.acceptInfo());
        return acceptService.acceptInfo();
    }
}
