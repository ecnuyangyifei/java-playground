package com.yifeiyang.generic;

import java.util.function.Consumer;

public class GenericMain {

    public static void main(String[] args) {

//        Holder<String> h;
//        h = new Holder<>();
    }

    static class Holder<T extends Consumer<T>> {
        private T t;
        void set(T t) {
            this.t = t;
        }

        T get() {
            return this.t;
        }
    }
}
