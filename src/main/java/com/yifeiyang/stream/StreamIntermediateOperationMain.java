package com.yifeiyang.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamIntermediateOperationMain {
    public static void main(String[] args) {
        concatStream();
        limitStream();
        skipStream();
        filterStream();
        sortStream();
        distinctStream();
        mapStream();
        flatMapStream();
        peekStream();
    }



    private static void concatStream() {
        Stream<String> a = Stream.of("Java", "Mooc");
        Stream<String> b = Stream.of("Advanced", "Stream");

        System.out.println(Stream.concat(a, b).count());

    }

    private static void limitStream() {
        Stream<String> wordsStream = Stream.of("Java", "MOOC", "Advanced", "Stream");

        wordsStream.limit(2).forEach(System.out::println);
    }

    private static void skipStream() {
        Stream<String> wordsStream = Stream.of("Java", "MOOC", "Advanced", "Stream");

        wordsStream.skip(2).forEach(System.out::println);
    }

    private static void filterStream() {
        Stream<String> wordsStream = Stream.of("Java", "MOOC", "Advanced", "Stream");

        wordsStream.filter(s -> s.length() > 4).forEach(System.out::println);
    }

    private static void sortStream() {
        Stream<String> wordsStream = Stream.of("Java", "MOOC", "Advanced", "Stream");
        wordsStream.sorted((a, b) -> b.length() - a.length()).forEach(System.out::println);
    }

    private static void distinctStream() {
        Stream<String> wordsStream = Stream.of("Java", "MOOC", "Java", "Stream", "Advanced", "Stream");
        wordsStream.distinct().forEach(System.out::println);
    }

    private static void mapStream() {
        Stream.generate(Math::random).limit(5).map(Math::round).forEach(System.out::println);
    }

    private static void flatMapStream() {
        List<List<String>> courses = Arrays.asList(
                Arrays.asList("Java-1", "Java-2", "Java-3"),
                Arrays.asList("OS-1", "OS-2", "OS-3")
        );
        courses.stream().forEach(System.out::println);

        courses.stream().flatMap(cs -> cs.stream()).forEach(System.out::println);
    }

    private static void peekStream() {
        Stream<String> wordsStream = Stream.of("Java", "MOOC", "Advanced", "Stream");

        wordsStream.peek(System.out::println).map(s -> "mapped " + s).forEach(System.out::println);
    }
}
