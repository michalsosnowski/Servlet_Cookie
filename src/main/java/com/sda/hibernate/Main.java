package com.sda.hibernate;

import com.sda.hibernate.config.HibernateUtils;
import com.sda.hibernate.entity.Author;
import com.sda.hibernate.entity.Book;
import com.sda.hibernate.entity.Category;
import com.sda.hibernate.service.AuthorService;
import com.sda.hibernate.service.BookService;
import com.sda.hibernate.service.CategoryService;

import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(final String[] args)   {

        CategoryService categoryService = new CategoryService();
        AuthorService authorService = new AuthorService();

        Category category = new Category();
        category.setName("Nowa_uwaga_na_duplikaty");
        categoryService.save(category);

        Author author = new Author();
        author.setName("Nowy autor");

        authorService.save(author);

        BookService bookService = new BookService();

        Author author1 = new Author();
        author1.setName("Autor");
        author1.setLastname("Nazwisko");

        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author1);

        Category category1 = new Category();
        category1.setName("TESTOWA");

        Book s = new Book();
        s.setCategory(category1);
        s.setAuthors(authorSet);
        s.setTitle("Tytul");
        bookService.save(s);


//        for(Book book: bookService.findAll()){
//            System.out.println("Tytul: " + book.getTitle());
//            System.out.println("Autorzy: ");
//            for (Author a: book.getAuthors()){
//                System.out.println(a.getName() + " " + a.getLastname());
//            }
//            System.out.println("Kategoria: " + book.getCategory().getName());
//
//        }

//        HibernateUtils.closeConnection();
    }
}
