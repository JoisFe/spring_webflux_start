package com.example.springReactiveHeadOffice;

import com.example.springReactiveHeadOffice.dto.Book;
import java.net.URI;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication
public class SpringReactiveHeadOfficeApplication {

    private URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
                                              .host("localhost")
                                              .port(6060)
                                              .path("/v1/books")
                                              .build()
                                              .encode()
                                              .toUri();

    public static void main(String[] args) {
        System.setProperty("reactor.netty.ioWorkerCount", "1");
        SpringApplication.run(SpringReactiveHeadOfficeApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return (String... args) -> {
            log.info("# 요청 시작 시간 : {}", LocalDateTime.now());

            for (int i = 1; i <= 5; ++i) {
                // Book book = this.getBook(i) 을 쓰지 않고 subscribe() 에서 응답 데이터를 전달받은 후에 처리
                // HeadOffice API 로부터 전달받은 응답이 Mono 타입이고 Mono 는 Reactor 에서 지원하는 Publisher 타입 중 하나이기 때문
                // Publisher 인터페이스 경우 subscribe() 를 호출하여 전달받은 데이터 처리하도록 정의
                // Mono 또한 Reactor 에서 지원하는 Publisher 타입
                this.getBook(i)
                    .subscribe(
                        book -> log.info("{} : book name: {}", LocalDateTime.now(), book.getName())
                    );
            }
        };
    }

    private Mono<Book> getBook(long bookId) {
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                                             .path("/{book-id}")
                                             .build()
                                             .expand(bookId)
                                             .encode()
                                             .toUri(); // http://localhost:6060/v1/books/{book-id}

        return WebClient.create()
                        .get()
                        .uri(getBookUri)
                        .retrieve()
                        .bodyToMono(Book.class);
    }
}
