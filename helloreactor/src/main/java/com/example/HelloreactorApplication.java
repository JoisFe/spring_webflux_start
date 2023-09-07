package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class HelloreactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloreactorApplication.class, args);

        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence.map(String::toLowerCase)
            .subscribe(System.out::println);
    }
}
