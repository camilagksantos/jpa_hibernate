package com.jpa_hibernate.entities;

import jakarta.persistence.Entity;

@Entity
public class Book extends ProductInheritance{

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
