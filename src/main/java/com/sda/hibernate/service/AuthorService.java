package com.sda.hibernate.service;

import com.sda.hibernate.dao.AuthorDao;
import com.sda.hibernate.entity.Author;

public class AuthorService {

    private static AuthorDao authorDao;

    public AuthorService() {
        authorDao = new AuthorDao();
    }

    public void save(Author author){
        authorDao.openCurrentSession();
        authorDao.save(author);
        authorDao.closeCurrentSession();
    }
}
