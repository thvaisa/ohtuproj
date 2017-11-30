package com.ohtuproj;


import com.ohtuproj.controllers.AddController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Running..");
        SpringApplication.run(Main.class, args);
    }
}
