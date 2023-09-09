package com.example.springReactiveBranchOffice.controller;

import com.example.springReactiveBranchOffice.domain.Book;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/v1/books")
@RestController
@AllArgsConstructor
public class SpringReactiveBranchOfficeController {

    private Map<Long, Book> bookMap;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") long bookId) throws InterruptedException {
        // HeadOffice 요청을 처리하는 스레드가 차단되는지 BranchOffice 의 요청을 처리하는 스레드 5초를 sleep 하여 시간 확인
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);

        return Mono.just(book);
    }
}
