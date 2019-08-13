package com.yifeiyang;

import java.util.concurrent.Callable;

public class LambdaMain {
    public static void main(String[] args) throws Exception {
        LambdaMain lm = new LambdaMain();
        Callable<Integer> lambdaCall = lm.lambdaClosure();
        lambdaCall.call();
        lambdaCall.call();
        lambdaCall.call();

        Callable<Integer> anonymousCall = lm.anonymousInnerClass();
        anonymousCall.call();
        anonymousCall.call();
        anonymousCall.call();

    }

    private int cnt = 0;
    private Callable<Integer> lambdaClosure() {

        Callable<Integer> increate = () -> {
            System.out.println(cnt);
            return cnt++;
        };

        return increate;
    }

    private Callable<Integer> anonymousInnerClass() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(cnt);
                return cnt++;
            }
        };
    }

}
