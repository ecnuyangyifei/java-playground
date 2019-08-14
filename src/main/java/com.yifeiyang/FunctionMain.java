package com.yifeiyang;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionMain {
    public static void main(String[] args) {
        runnableExample();
        funcExample();
        biConsumerExample();
        predicateExample();
    }

    private static void runnableExample() {
        System.out.println(Thread.currentThread().getName());
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

    private static void funcExample() {
        System.out.println(getDateString(d -> d.getTime().toString()));
        System.out.println(getDateString(d -> String.format("%d/%d/%d", d.get(Calendar.YEAR), d.get(Calendar.MONTH) + 1, d.get(Calendar.DATE))));
    }

    private static String getDateString(Function<Calendar, String> dateConverter) {
        Calendar c = Calendar.getInstance();
        return dateConverter.apply(c);
    }

    private static void biConsumerExample() {
        consumeMap((k, v) -> System.out.println(String.format("k:%s,v:%s", k, v)));
        consumeMap((k, v) -> System.out.println(String.join("," , k, v)));
    }

    private static void consumeMap(BiConsumer<String, String> mapEntryConsumer) {
        Map<String, String> m = new HashMap<>();
        m.put("name", "Java");
        m.put("version", "1.8");
        m.entrySet().forEach(e -> mapEntryConsumer.accept(e.getKey(), e.getValue()));
    }


    private static void predicateExample() {
        filterList(s -> s.length() > 3);
        filterList(s -> s.startsWith("j"));
    }

    private static void filterList(Predicate<String> filter) {
        List<String> words = new LinkedList<>();
        words.add("I");
        words.add("love");
        words.add("java");
        words.add("love");
        words.add("coding");
        System.out.println(words.stream().filter(filter).collect(Collectors.joining(",")));
    }

}
