package com.camilo.springbootplatzi.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImpl implements MyBeanWithDependency {
    private final MyOperation myOperation;

    @Autowired
    public MyBeanWithDependencyImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDepedency() {
        System.out.println(myOperation.sum(2));
        System.out.println("hola desde la implementacion de My bean with dependency");
    }
}
