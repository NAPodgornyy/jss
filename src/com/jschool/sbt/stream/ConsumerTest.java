package com.jschool.sbt.stream;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        print("Hello", s -> System.out.println(s));
        //fibonacci();
    }

    private static void print(String text, Consumer<String> consumer){
        consumer.accept(text);

    }

}
