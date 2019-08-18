package com.yifeiyang.stream;

import java.util.List;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTerminationOperationMain {
    public static void main(String[] args) {
        reduceStream();
        collectStream();
    }

    private static void reduceStream() {
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + b;
        System.out.println(Stream.of(1, 2, 3, 4, 5).reduce(0, sumAccumulator));
        System.out.println(Stream.of(1, 2, 3, 4, 5).reduce(sumAccumulator).get());
    }

    private static void collectStream() {
        System.out.println(Stream.of(1, 2, 3).collect(Collectors.toList()));

        System.out.println(Stream.of(1, 2, 3).collect(Collectors.toSet()));

        System.out.println(Stream.of(1, 2, 3).collect(Collectors.toMap(k -> "k:" + k, v -> "v:" + v.toString())));

        Collector<Integer, ?, TreeSet<Integer>> myTreeSetCollector = Collector.of(TreeSet::new, TreeSet::add, (left, right) -> {
            left.addAll(right);
            return left;
        });
        System.out.println(Stream.of(1, 2, 3, 4, 4, 4, 5).collect(myTreeSetCollector));
    }
}
