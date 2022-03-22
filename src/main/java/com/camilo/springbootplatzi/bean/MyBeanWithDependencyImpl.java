package com.camilo.springbootplatzi.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImpl implements MyBeanWithDependency {
    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImpl.class);
    private final MyOperation myOperation;

    @Autowired
    public MyBeanWithDependencyImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDepedency() {
        LOGGER.info("Entramos al metodo printWithDependency");
        int num = 1;
        System.out.println(myOperation.sum(num));
        LOGGER.debug("El numero enviado es "+ num);
        System.out.println("hola desde la implementacion de My bean with dependency");
    }
}
