package com.ohtuproj.dbmodels;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lukuvinkki_id")
    private String lukuvinkkiId;

    @Column(name = "date")
    private Date date;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "ISBN")
    private String ISBN;
    
    @Column(name = "name")
    private String name;
    
    @ManyToMany
    List<Tag> tags;

    public Book(){

    }

    public Book(String name){
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLukuvinkkiId() {
        return lukuvinkkiId;
    }

    public void setLukuvinkkiId(String lukuvinkkiId) {
        this.lukuvinkkiId = lukuvinkkiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDate()
    {
        if(this.date != null) {
            SimpleDateFormat format = new SimpleDateFormat("MM.dd.yyyy");
            return format.format(this.date);
        }
        return "-";
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
     
}