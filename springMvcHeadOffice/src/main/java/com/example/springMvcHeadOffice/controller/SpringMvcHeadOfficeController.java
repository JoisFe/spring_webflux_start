package com.example.springMvcHeadOffice.controller;

import com.example.springMvcHeadOffice.dto.Book;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/books")
public class SpringMvcHeadOfficeController {

    private final RestTemplate restTemplate;

    URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
                                      .host("localhost")
                                      .port(7070)
                                      .path("v1/books")
                                      .build()
                                      .encode()
                                      .toUri();

    /**
     * HeadOffice 에서 특정 bookId를 통해 책을 찾아달라는 요청을 받으면
     * RestTemplate 을 통하여 BranchOffice에 책을 찾아달라는 요청을 함 (Blocking I/O)
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) {
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri(); // http://localhost:7070/v1/books/{book-id}

        ResponseEntity<Book> response = restTemplate.getForEntity(getBookUri, Book.class);
        Book book = response.getBody();

        return ResponseEntity.ok(book);
    }
}
