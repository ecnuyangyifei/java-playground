package com.yifeiyang.stream;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class StreamFeatureMain {
    public static void main(String[] args) {
//        onceStream();

//        lazyStream();

        parallelStream();
    }

    private static void onceStream() {
        IntStream iStream = IntStream.range(1, 5);
        iStream.count();
        iStream.count(); //will throw exception
    }

    private static void lazyStream() {
        IntStream intStream = IntStream.range(1, 100);
        IntStream filtered = intStream.filter(i -> i % 2 == 0);
        IntStream mapped = filtered.map(i -> i / 2);
        System.out.println(mapped.sum()); //
    }

    private static void parallelStream() {
        long CNT = 999999999;
        Runnable serializationWay = () -> {
            LongStream.range(1, CNT).map(i -> i + 1).sum();
        };

        Runnable parallelWay = () -> {
            LongStream.range(1, CNT).parallel().map(i -> i + 1).sum();
        };

        measureFunc(serializationWay, "serialization way");
        measureFunc(parallelWay, "parallel way");

    }

    private static void measureFunc(Runnable runnable, String msg) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        System.out.println(msg + " using " + (end - start) + " ms");
    }
}
