package com.liuhu.learning.config;

import com.liuhu.learning.dto.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author liuhu-jk
 * @Date 2019/11/15 10:40
 * @Description
 **/

@Configuration
@ComponentScan("com.liuhu.learning")
public class MyEventListenerConfig {

      @Bean
      public Person getPerson(){
          return new Person();
      }


}
