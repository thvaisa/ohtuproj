package com.ohtuproj.controllers;

import com.ohtuproj.dbmodels.Book;
import com.ohtuproj.dbmodels.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AddController {

    @Autowired
    BookRepository repository;

    @RequestMapping("/")
    String main(Map<String, Object> model)
    {
        String books = "\n";
        for(Book b : repository.findAll()){
            books += b.getName() + "\n";
        }
        model.put("books", books);

        return "main";
    }

    @RequestMapping(value = "/add", method=RequestMethod.POST)

    String add(@RequestBody MultiValueMap<String,String> formData){
        List<String> bookName = formData.get("bookName");
        if(bookName != null) {
            // Add new book using name
            Book test = new Book(bookName.get(0));
            repository.save(test);
        }

        // Return to mainpage
        return "redirect:/";
    }


}