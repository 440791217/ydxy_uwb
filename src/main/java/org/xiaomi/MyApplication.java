package org.xiaomi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/hello")
    String home1() {
        return "Hello World1!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}