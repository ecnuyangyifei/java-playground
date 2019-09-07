package com.yifeiyang.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class GenericMain {

    /**
     * Java 泛型的类型是不变的，List<String>, 与List<Object>是完全没有关系的类型，List<String>不能代替List<Object>
     * 而对于类型String 和Object则是由关系的，String继承自Object，在任何需要Object的地方都可以由String代替,反之则不可以，是协变的
     * Java数组也是协变的，即是String[]可以代替Object[]
     * 
     * 声明List<Object>,可以添加String,Number...各种类型，因为Java类型是协变的，但List<Object>这一泛型类型是不变的
     * 
     * 对于泛型的通配以及上下界限定的类型，实际上是限定该泛型类型的取值集合，该泛型类型是集合中的一种类型
     * 
     * 
     * 对于? extends T，假定S:{T, T1, T2 ... Tn}是T类型的所有子类型及其自身，那么? extends T所定义的泛型类型是S里其中一个类型Ti
     * 类型Ti必然是T或其子类，所以get该类型时，类型为T，但不能set该类型，因为Java类型是协变的，set任何类型都是不安全的
     * 
     * 对于? super T,所限定的类型集合是T及其超类,由于Java类型是协变的，可以set T及其子类型；而获得该类型时只能取最上界Object才是安全的
     * 
     * 
     * @param args
     */
    public static void main(String[] args) {

       ListHolder<A> as = new ListHolder<>();
       as.add(new B());
       as.add(new A());
       as.get().stream().forEach(System.out::println);

       A[] aArr = {
           new A(),
           new B()
       };
       Arrays.stream(aArr).forEach(System.out::println);

       List<A> ls = new ArrayList<>();
       ls.add(new A());
       ls.add(new B());

       covariant();
       contravariant();
    }

    static void covariant() {
        Consumer<List<? extends A>> c = as -> as.stream().forEach(System.out::println);

        List<B> bs = Arrays.asList(new B());
        c.accept(bs);

        Consumer<A[]> c1 = as -> Arrays.stream(as).forEach(System.out::println);
        c1.accept(new B[]{new B()});

        // Consumer<List<A>> c2 = as -> as.stream().forEach(System.out::println);
        // c2.accept(bs); // compile error
    }

    static void arrayCovariant() {
        Consumer<Object[]> c = os -> {};
        c.accept(new String[]{});
    }

    static void contravariant() {
        Consumer<List<? super B>> c = as -> as.stream().forEach(System.out::println);
        List<A> as = Arrays.asList(new A(), new B(), new C());
        c.accept(as);
    }

    static void pecs() {
        List<? extends A> as = new ArrayList<>();
        // as.add(new A()); // Error Parent can not replace child

        List<? super B> bs = new ArrayList<>();
        bs.add(new B());
//        bs.add(new A());
        bs.add(new C());
        bs.get(0); // Object
    }

    static class A {

    }

    static class B extends A {

    }

    static class C extends B {

    }

    static class ListHolder<T> {
        private List l = new ArrayList<>();
        <Y extends T> void add(Y t) {
            l.add(t);
        }



        List<? extends T> get() {
            return l;
        }
    }

    static class Holder<T> {
        private Object o;
        <Y extends T> void set(Y t) {
            this.o = t;
        }

        T get() {
            return (T)o;
        }
    }
}
