package com.example.mono;

import reactor.core.publisher.Flux;

public class Example5 {

    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
            .filter(num -> num > 6)
            .map(num -> num * 2)
            .subscribe(System.out::println);
    }
}
