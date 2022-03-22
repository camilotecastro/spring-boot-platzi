package com.camilo.springbootplatzi.configuration;

import com.camilo.springbootplatzi.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBeanTwoImpl();
    }

    @Bean
    public MyOperation beanOperationTwo(){
        return new MyOperationImpl();
    }

    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImpl(myOperation);
    }
}
