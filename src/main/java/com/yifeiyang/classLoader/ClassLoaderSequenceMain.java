package com.yifeiyang.classLoader;

/***
 * Specify -verbose:class as JVM option to see class loading sequence
 */
public class ClassLoaderSequenceMain {
    public static void main(String[] args) {
        System.out.println("Before call Math.random()");
        System.out.println(Math.random());
    }
}
