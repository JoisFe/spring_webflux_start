package com.example.springReactiveBranchOffice;

import com.example.springReactiveBranchOffice.domain.Book;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringReactiveBranchOffice {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveBranchOffice.class, args);
    }

    @Bean
    public Map<Long, Book> bookMap() {
        Map<Long, Book> bookMap = new HashMap<>();

        for (long i = 1; i <= 5; ++i) {
            bookMap.put(i, new Book(i, "book" + i));
        }

        return bookMap;
    }
}
