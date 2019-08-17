package com.yifeiyang.classLoader;

import java.util.function.Consumer;

public class Echo implements Consumer<String> {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
}
