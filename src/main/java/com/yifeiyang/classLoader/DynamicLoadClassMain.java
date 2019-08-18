package com.yifeiyang.classLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class DynamicLoadClassMain implements Consumer {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader appClassLoader = DynamicLoadClassMain.class.getClassLoader();
        Constructor<? extends Consumer> echoConstructor = appClassLoader.loadClass("com.yifeiyang.classLoader.DynamicLoadClassMain").asSubclass(Consumer.class).getConstructor();

        Consumer<String> echo = echoConstructor.newInstance();
        echo.accept("loadClass");

         Constructor<? extends  Consumer> echoConstructor1 = Class.forName("com.yifeiyang.classLoader.DynamicLoadClassMain", true, appClassLoader).asSubclass(Consumer.class).getConstructor();
         echoConstructor1.newInstance().accept("forName");
    }

    @Override
    public void accept(Object o) {
        System.out.println(o);
    }
}
