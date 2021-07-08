package com.example.test_sql;

import com.example.test_sql.model.Khoa;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@RequiredArgsConstructor
public class TestSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSqlApplication.class, args);

    }

}
