package main.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AsdApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AsdApplication.class, args);
    }
}