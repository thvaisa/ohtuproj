package com.ohtuproj;

import com.ohtuproj.dbmodels.Book;
import com.ohtuproj.dbmodels.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class AddController {

    @Autowired
    BookRepository repository;

    @RequestMapping("/")
    String home()
    {
        String books = "";
        for(Book b : repository.findAll()){
            books += b.getId() + ":" + b.getName() + "<br>";
        }
        return books;
    }


    @RequestMapping("/add")
    String add()
    {
        Book test = new Book("Test");
        repository.save(test);
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AddController.class, args);
    }

}