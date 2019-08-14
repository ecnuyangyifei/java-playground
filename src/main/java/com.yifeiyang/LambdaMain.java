package com.yifeiyang;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaMain {
    public static void main(String[] args) throws Exception {
        classSort();
        anonymousSort();
        lambdaSort();
        methodReferenceSort();
    }

    private static void classSort() {
        String[] words = getUnsortedWords();
        printArray(words);
        Arrays.sort(words, new CompareUsingLength());
        printArray(words);
    }

    private static void anonymousSort() {
        String[] words = getUnsortedWords();
        printArray(words);
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        printArray(words);
    }


    private static void lambdaSort() {
        String[] words = getUnsortedWords();
        printArray(words);
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        printArray(words);
    }

    private static void methodReferenceSort() {
        String[] words = getUnsortedWords();
        printArray(words);
        Arrays.sort(words, LambdaMain::compareStringUsingLength);
        printArray(words);
    }


    private static int compareStringUsingLength(String a, String b) {
        return a.length() - b.length();
    }



    static class CompareUsingLength implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static String[] getUnsortedWords() {
        return new String[]{
                "hello",
                "hi",
                "world",
                "java"
        };
    }

    private static <T> void printArray(T[] ts) {
        List l = Arrays.asList(ts);
        System.out.println(String.join(",", l));
    }

}
