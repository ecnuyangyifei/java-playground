package com.yifeiyang.stream;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CreateStreamMain {
    public static void main(String[] args) {
        fromArray();
        fromCollection();
        fromStreamGenerate();
        fromStreamIterate();
        fromStreamOf();
        fromStreamBuilder();
        other();
    }


    private static void fromArray() {
        String[] words = {"ECNU", "Java", "MOOC"};

        Arrays.stream(words).forEach(System.out::println);
    }

    private static void fromCollection() {
        List<String> words = Arrays.asList("ECNU", "Java", "MOOC");

        words.stream().forEach(System.out::println);

        Set<String> wordSet = new HashSet<>(words);

        wordSet.stream().forEach(System.out::println);
    }

    private static void fromStreamGenerate() {
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
    }

    private static void fromStreamIterate() {
        Stream.iterate(0, num -> num + 1).limit(10).forEach(System.out::println);
    }

    private static void fromStreamOf() {
        Stream.of("Java", "Mooc", "ECNU").map(s -> "from of " + s).forEach(System.out::println);
    }

    private static void fromStreamBuilder() {
        Stream.builder()
                .add("ECNU")
                .add("Java")
                .add("MOOC")
                .build().map(s -> "from builder" + s).forEach(System.out::println);

    }

    private static void other() {
        String sentence = "ECNU Java MOOC";
        Pattern.compile("\\W").splitAsStream(sentence).forEach(System.out::println);
    }
}
