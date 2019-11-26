package com.liuhu.learning.service.impl;

import com.liuhu.learning.annotation.DefAutowired;
import com.liuhu.learning.service.AcceptService;
import com.liuhu.learning.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author liuhu-jk
 * @Date 2019/11/9 17:23
 * @Description
 **/
public class AcceptServiceImpl  implements AcceptService {
    //@Autowired
   // @DefAutowired
    private HelloService helloService;

    public void setHelloService(HelloService helloService) {
        System.out.println("Accept调用hellosetter方法。。。。。。");
        this.helloService = helloService;
    }

    public HelloService getHelloService() {
        return helloService;
    }

    @Override
    //@DefAutowired
    public String acceptInfo() {
        return  "神奇的结果1";
    }
}
