package com.example.springMvcBranchOffice;

import com.example.springMvcBranchOffice.domain.Book;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringMvcBranchOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcBranchOfficeApplication.class, args);
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
