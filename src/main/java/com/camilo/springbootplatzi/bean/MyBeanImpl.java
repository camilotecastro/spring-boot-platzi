package com.camilo.springbootplatzi.bean;


public class MyBeanImpl implements MyBean {
    @Override
    public void print() {
        System.out.println("Hola desde mi implementacion propia de mi  bean");
    }
}
