package com.yifeiyang;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class LambdaVsAnonymous {
    private int cnt = 0;

    public void increate() throws Exception {
        Callable<Integer> increateAndReturn = () -> ++this.cnt;
        System.out.println(increateAndReturn.call());

        Callable<Integer> increateAndReturnAnomous = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return ++LambdaVsAnonymous.this.cnt;
            }
        };

        System.out.println(increateAndReturnAnomous.call());

    }

    public static void main(String[] args) throws Exception {
        new LambdaVsAnonymous().increate();
    }
}
