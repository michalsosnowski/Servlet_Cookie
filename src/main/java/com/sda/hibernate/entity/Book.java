package com.sda.hibernate.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Author> authors;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;
    }

    public Book() {
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
