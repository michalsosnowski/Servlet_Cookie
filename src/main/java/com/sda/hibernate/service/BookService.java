package com.sda.hibernate.service;

import com.sda.hibernate.dao.BookDao;
import com.sda.hibernate.entity.Book;

import java.util.List;

public class BookService {

    private static BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }

    public List<Book> findAll(){
        bookDao.openCurrentSession();
        List<Book> bookList = bookDao.findAll();
        bookDao.closeCurrentSession();
        return bookList;
    }

    public Book save(Book book){
        bookDao.openCurrentSession();
        bookDao.save(book);
        bookDao.closeCurrentSession();
        return book;
    }
}
