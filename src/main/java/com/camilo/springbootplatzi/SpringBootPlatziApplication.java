package com.camilo.springbootplatzi;

import com.camilo.springbootplatzi.bean.MyBean;
import com.camilo.springbootplatzi.bean.MyBeanWithDependency;
import com.camilo.springbootplatzi.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPlatziApplication implements CommandLineRunner {
    private final ComponentDependency componentDependency;
    private final MyBean myBean;
    private final MyBeanWithDependency myBeanWithDependency;

    @Autowired
    public SpringBootPlatziApplication(
            @Qualifier("componenteTwoImplement") ComponentDependency componentDependency,
            MyBean myBean,
            MyBeanWithDependency myBeanWithDependency) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlatziApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDepedency();
    }
}
