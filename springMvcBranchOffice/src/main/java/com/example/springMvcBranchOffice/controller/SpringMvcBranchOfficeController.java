package com.example.springMvcBranchOffice.controller;

import com.example.springMvcBranchOffice.domain.Book;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
public class SpringMvcBranchOfficeController {

    private Map<Long, Book> bookMap;

    @Autowired
    public SpringMvcBranchOfficeController(Map<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }

    /**
     * HeadOffice 에서 bookId 를 통해 책을 조회하는 요청이 왔음
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) throws InterruptedException {
        // HeadOffice 요청을 처리하는 스레드가 차단되는지 BranchOffice 의 요청을 처리하는 스레드 5초를 sleep 하여 timeout 이 나는지 확인
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);

        return ResponseEntity.ok(book);
    }
}
