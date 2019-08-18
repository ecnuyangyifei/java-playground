package com.yifeiyang.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

/**
 *本例实现类加载器采用最简单的方式，也是推荐的方式
 *仅重写父类（ClassLoader）findClass方法
 *该加载器工作流程同默认加载方式相同（委托给父加载器）
 *只有当父加载器都无法加载类时会触发自定义的处理逻辑
 *为简化代码，加载类时传了错误的类名，以此来调用自定义的加载逻辑，这只是为了演示类加载机制以及自定义类加载器的方式，实际应用应避免 
 */
public class MyClassLoaderUsingDelegation extends ClassLoader implements Consumer {

    @Override
    public void accept(Object o) {
        System.out.println(o);
    }

    private static final String WRONG_CLAZZ_NAME = "wrong";
    private static final String REAL_CLAZZ_NAME = "com.yifeiyang.classLoader.MyClassLoaderUsingDelegation";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        ClassLoader appClassLoader = new MyClassLoaderUsingDelegation();
        Constructor<? extends Consumer> echoConstructor = appClassLoader.loadClass(WRONG_CLAZZ_NAME).asSubclass(Consumer.class).getConstructor();

        Consumer<String> echo = echoConstructor.newInstance();
        echo.accept("hi");
    }

    private static int BUFFER_SIZE = 4096;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String clazzUrl = convertClazzNameToURI(REAL_CLAZZ_NAME);
        InputStream clazzInputStream = MyClassLoaderUsingDelegation.class.getResourceAsStream(clazzUrl);
        if(clazzInputStream == null) {
            System.out.println(REAL_CLAZZ_NAME + " not locates in " + clazzUrl);
            System.out.println("Please compile " + clazzUrl + " firstly");
            throw new ClassNotFoundException(REAL_CLAZZ_NAME);
        }
        try {
            byte[] clazzBytes = inputStreamToBytes(clazzInputStream);
            System.out.println(name + " loaded using my classloader");
            return super.defineClass(REAL_CLAZZ_NAME, clazzBytes, 0, clazzBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    private String convertClazzNameToURI(String name) {
        return "/" + name.replaceAll("\\.", "/") + ".class";
    }

    private static byte[] inputStreamToBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];

        int nRead;
        while ((nRead = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, nRead);
        }

        return out.toByteArray();
    }


}
