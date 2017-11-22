package com.ohtuproj.controllers;

import com.ohtuproj.dbmodels.Book;
import com.ohtuproj.dbmodels.BookRepository;
import com.ohtuproj.dbmodels.Tag;
import com.ohtuproj.dbmodels.TagRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AddController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TagRepository tagRepository;

    @RequestMapping("/")
    String main(Map<String, Object> model) {
        String books = "\n";
        for (Book b : bookRepository.findAll()) {
            books += b.getName() + "\n";
        }
        model.put("books", books);

        return "main";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)

    String add(@RequestBody MultiValueMap<String, String> formData) {
        List<String> bookName = formData.get("bookName");
        List<String> tagsList = formData.get("tags");
        if (bookName != null) {
            // Add new book using name
            Book book = new Book(bookName.get(0));
            bookRepository.save(book);
            List<Tag> bookTags = new ArrayList();
            // Split the tags string and connect them to book
            if (tagsList != null) {
                String[] tags = tagsList.get(0).split(" ");
                for (int i = 0; i < tags.length; i++) {
                    Tag tag = tagRepository.findByName(tags[i]);
                    // check if tag exists, create if not
                    if (tag == null) {
                        tag = new Tag();
                        tag.setName(tags[i]);
                    }
                    bookTags.add(tag);
                    List<Book> tagBooks = tag.getBooks();
                    tagBooks.add(book);
                    tag.setBooks(tagBooks);
                    tagRepository.save(tag);
                }
            }
            book.setTags(bookTags);
            bookRepository.save(book);
        }
        // Return to mainpage
        return "redirect:/";
    }
}
