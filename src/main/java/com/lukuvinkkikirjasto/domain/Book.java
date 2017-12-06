/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lukuvinkkikirjasto.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book extends AbstractPersistable<Long> {

    private String name;
    private String author;
    private String ISBN;
    private String date;

    @ManyToMany
    private List<Tag> tags;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getDate() {
        return date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags){
        this.tags = tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
