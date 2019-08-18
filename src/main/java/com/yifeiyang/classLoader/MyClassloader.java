package com.yifeiyang.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class MyClassloader extends ClassLoader {



    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        ClassLoader myClassloader = new MyClassloader();
        Constructor<? extends Consumer> echoConstructor = myClassloader.loadClass("com.yifeiyang.classLoader.EchoUsingMyClassLoader").asSubclass(Consumer.class).getConstructor();

        Consumer<String> echo = echoConstructor.newInstance();
        echo.accept("hi");
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized(this.getClassLoadingLock(name)) {
            if(name != null && name.contains("MyClassLoader")) {
                Class foundClazz = this.findLoadedClass(name);
                if(foundClazz != null) {
                    return foundClazz;
                }
                String clazzUrl = convertClazzNameToURI(name);
                InputStream clazzInputStream = MyClassloader.class.getResourceAsStream(clazzUrl);
                if(clazzInputStream == null) {
                    System.out.println(name + " not locates in " + clazzUrl);
                    System.out.println("Please compile " + clazzUrl + " firstly");
                    throw new ClassNotFoundException(name);
                }
        
                try {
                    byte[] clazzBytes = inputStreamToBytes(clazzInputStream);
                    System.out.println(name + " loaded using my classloader");
                    return super.defineClass(name, clazzBytes, 0, clazzBytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(e.getMessage());
                }
            } else  {
                return super.loadClass(name);
            }
        }
    }

    private String convertClazzNameToURI(String name) {
        return "/" + name.replaceAll("\\.", "/") + ".class";
    }

    private static int BUFFER_SIZE = 4096;

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
