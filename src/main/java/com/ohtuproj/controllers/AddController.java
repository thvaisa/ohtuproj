package com.ohtuproj.controllers;

import com.ohtuproj.dbmodels.Book;
import com.ohtuproj.dbmodels.BookRepository;
import com.ohtuproj.dbmodels.Tag;
import com.ohtuproj.dbmodels.TagRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;

@Controller
public class AddController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TagRepository tagRepository;

    @RequestMapping("/")
    String main(Map<String, Object> model) {
        return "main";
    }

    @RequestMapping("/books")
    String books(Model model) {
        model.addAttribute("books", this.bookRepository.findAll());
        return "books";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String add(@RequestBody MultiValueMap<String, String> formData) {
        List<String> bookName = formData.get("bookName");
        List<String> tagsList = formData.get("tags");
        List<String> bookDate = formData.get("bookDate");
        List<String> bookISBN = formData.get("bookISBN");
        List<String> bookAuthor = formData.get("bookAuthor");
        if (bookName != null) {
            // Add new book using name
            Book book = new Book(bookName.get(0));
            book.setDate(parseDate(bookDate.get(0)));
            book.setAuthor(bookAuthor.get(0));
            book.setISBN(bookISBN.get(0));

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
        return "redirect:/books";
    }

    private static Date parseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("DD-MM-yyyy");
        try {
            Date parsedDate = format.parse(date);
            return parsedDate;
        } catch (Exception e) {
            System.err.println("invalid date: " + e);
        }
        return null;
    }
}
