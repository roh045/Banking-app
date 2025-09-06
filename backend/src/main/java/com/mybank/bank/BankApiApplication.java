package com.mybank.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BankApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }
}
