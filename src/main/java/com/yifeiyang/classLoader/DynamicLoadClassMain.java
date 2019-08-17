package com.yifeiyang.classLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class DynamicLoadClassMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader appClassLoader = DynamicLoadClassMain.class.getClassLoader();
        Constructor<? extends Consumer> echoCCtr = appClassLoader.loadClass("com.yifeiyang.classLoader.Echo").asSubclass(Consumer.class).getConstructor();

        Consumer<String> echo = echoCCtr.newInstance();
        echo.accept("hi");
    }
}
