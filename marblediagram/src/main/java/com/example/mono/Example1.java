package com.example.mono;

import reactor.core.publisher.Mono;

public class Example1 {

    /**
     * Reactor 의 Publisher 타입 중 하나인 Mono 를 사용하여 문자열 출력
     */
    public static void main(String[] args) {
        Mono.just("Hello Reactor") // 하나의 문자열!
            .subscribe(System.out::println);
    }
}
