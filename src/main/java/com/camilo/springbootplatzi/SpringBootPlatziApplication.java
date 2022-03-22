package com.camilo.springbootplatzi;

import com.camilo.springbootplatzi.bean.MyBean;
import com.camilo.springbootplatzi.bean.MyBeanWithDependency;
import com.camilo.springbootplatzi.component.ComponentDependency;
import com.camilo.springbootplatzi.configuration.MyBeanWithProperties;
import com.camilo.springbootplatzi.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPlatziApplication implements CommandLineRunner {
    private final Log LOGGER = LogFactory.getLog(SpringBootPlatziApplication.class);
    private final ComponentDependency componentDependency;
    private final MyBean myBean;
    private final MyBeanWithDependency myBeanWithDependency;
    private final MyBeanWithProperties myBeanWithProperties;
    private final UserPojo userPojo;

    @Autowired
    public SpringBootPlatziApplication(
            @Qualifier("componenteTwoImplement") ComponentDependency componentDependency,
            MyBean myBean,
            MyBeanWithDependency myBeanWithDependency,
            MyBeanWithProperties myBeanWithProperties,
            UserPojo userPojo) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlatziApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDepedency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword());
        try {
            int num = 10/0;
            LOGGER.debug("Mi numero " + num);
        }catch (Exception e){
            LOGGER.error("Division por 0 cero");
        }
    }
}
