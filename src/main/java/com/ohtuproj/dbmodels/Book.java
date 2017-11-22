package com.ohtuproj.dbmodels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "name")
    private String name;

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
}