package com.spring.pay.app.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
public class TaskApplication {
    public static void main(String[] args) {
//        SpringApplication.run(TaskApplication.class, args);
        System.out.println(Instant.now());
        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));
        LocalDateTime now = LocalDateTime.now();
        String format1 = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime localDateTime = now.minusSeconds(180);
        String format2 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format1);
        System.out.println(format2);
    }
}
