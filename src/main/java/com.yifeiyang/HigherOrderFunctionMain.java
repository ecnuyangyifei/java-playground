package com.yifeiyang;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class HigherOrderFunctionMain {
    public static void main(String[] args) {
        higherOrderFunc();
    }

    private static void higherOrderFunc() {
        int[] nums = new int[] {1, 2, 3, 4, 5};
        printNums(nums);
        int[] multi2 = map(nums, i -> i * 2);
        printNums(multi2);

        printNums(map(nums, getMapper("m2")));

        printNums(map(nums, getMapper("mm")));

        printNums(map(nums, getMapper("a2")));

    }

    private static IntFunction getMapper(String condition) {
        switch (condition) {
            case "m2":
                return i -> i * 2;
            case "mm":
                return i -> i * i;
            case "a2":
                return i -> i + 2;
            default:
                return i -> i;
        }
    }

    private static int[] map(int[] source, IntFunction mapper) {
        int[] target = new int[source.length];
        for(int i = 0; i < source.length; i++) {
            target[i] = (int) mapper.apply(source[i]);
        }
        return target;
    }

    private static void printNums(int[] nums) {
        System.out.print("[ ");
        for(int num : nums) {
            System.out.print(num);
            System.out.print(" ");
        }
        System.out.println("]");
    }
}
