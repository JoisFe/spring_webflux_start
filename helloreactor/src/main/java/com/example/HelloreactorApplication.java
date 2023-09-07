package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class HelloreactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloreactorApplication.class, args);

        Flux<String> sequence = Flux.just("Hello", "Reactor"); // 데이터 생성 및 제공
        sequence.map(String::toLowerCase) // 데이터 가공
                .subscribe(System.out::println); // 전달받은 데이터 처리
    }
}
