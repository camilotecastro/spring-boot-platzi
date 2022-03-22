package com.camilo.springbootplatzi.component;

import org.springframework.stereotype.Component;

@Component
public class ComponenteTwoImplement implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi segundo componente");
    }
}
