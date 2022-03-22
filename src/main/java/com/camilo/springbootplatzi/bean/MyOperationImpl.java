package com.camilo.springbootplatzi.bean;

public class MyOperationImpl implements MyOperation {
    @Override
    public int sum(int number) {
        return number + 1;
    }
}
