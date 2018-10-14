package com.sda.hibernate.service;

import com.sda.hibernate.dao.CategoryDao;
import com.sda.hibernate.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryServiceTest {

    private static CategoryDao categoryDao;

    @BeforeEach
    public void beforeTests(){
        categoryDao = new CategoryDao();
        categoryDao.openCurrentSession();
    }

    @Test
    void save() {

        Category category = new Category();
        category.setName("32333");
        assertNotNull(categoryDao.save(category));

    }
}