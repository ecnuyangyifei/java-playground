package com.yifeiyang;

import java.util.concurrent.Callable;

public class LambdaVsAnonymous {
    private int cnt = 0;

    public void increase() throws Exception {
        Callable<Integer> increaseAndReturn = () -> ++this.cnt;
        System.out.println(increaseAndReturn.call());

        Callable<Integer> increaseAndReturnAnonymous = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return ++LambdaVsAnonymous.this.cnt;
            }
        };

        System.out.println(increaseAndReturnAnonymous.call());

    }

    public static void main(String[] args) throws Exception {
        new LambdaVsAnonymous().increase();
    }
}
