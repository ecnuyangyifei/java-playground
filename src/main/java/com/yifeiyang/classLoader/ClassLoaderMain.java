package com.yifeiyang.classLoader;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class ClassLoaderMain {
    public static void main(String[] args) {
        System.out.println("ClassLoaderMain: " + getClassLoaderChain(ClassLoaderMain.class.getClassLoader()));
        System.out.println("java.sql.Date: " + getClassLoaderChain(Date.class.getClassLoader()));
        System.out.println("Object: " + getClassLoaderChain(Object.class.getClassLoader()));
    }

    private static String getClassLoaderChain(ClassLoader loader) {
        List<String> loaderChainBuilder = new LinkedList<>();
        loaderChainBuilder.add(getClassLoaderName(loader));
        ClassLoader tmp = loader;
        while (tmp != null) {
            tmp = tmp.getParent();
            loaderChainBuilder.add(getClassLoaderName(tmp));
        }
        return String.join("-->", loaderChainBuilder);
    }

    private static String getClassLoaderName(ClassLoader loader) {
        if(null == loader) {
            return "Bootstrap Class Loader";
        } else {
            return loader.toString();
        }
    }
}
