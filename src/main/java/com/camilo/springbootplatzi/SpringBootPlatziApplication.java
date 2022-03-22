package com.camilo.springbootplatzi;

import com.camilo.springbootplatzi.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPlatziApplication implements CommandLineRunner {
    private final ComponentDependency componentDependency;

    @Autowired
    public SpringBootPlatziApplication(
            @Qualifier("componenteTwoImplement") ComponentDependency componentDependency) {
        this.componentDependency = componentDependency;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlatziApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
    }
}
