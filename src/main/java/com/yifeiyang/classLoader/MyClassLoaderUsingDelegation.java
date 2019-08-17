package com.yifeiyang.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

/**
 *
 */
public class MyClassLoaderUsingDelegation extends ClassLoader {

    private static final String WRONG_CLAZZ_NAME = "wrong";
    private static final String REAL_CLAZZ_NAME = "com.yifeiyang.classLoader.EchoUsingMyClassLoader";

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
        InputStream clazzInputStream = MyClassloader.class.getResourceAsStream(clazzUrl);
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
