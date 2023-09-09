package com.example.mono;

import reactor.core.publisher.Mono;

public class Example2 {

    public static void main(String[] args) {
        Mono
            .empty() // 데이터 한 건도 emit 하지 않음
            .subscribe(
                none -> System.out.println("# emitted onNext Signal"),
                error -> {},
                () -> System.out.println("# emitted onComplete Signal")
                // 아무 데이터를 전송하지 emit 하지 않고 onComplete Signal 만을 전송
            );
    }
}
