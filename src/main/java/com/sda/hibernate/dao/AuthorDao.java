package com.sda.hibernate.dao;

import com.sda.hibernate.config.HibernateUtils;
import com.sda.hibernate.entity.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class AuthorDao implements DaoInterface<Author> {

    private Session currentSession;
    private Transaction currentTransaction;

    public Session getCurrentSession(){
        return currentSession;
    }

    public synchronized Session openCurrentSession(){
        if(currentSession == null) {
            currentSession = HibernateUtils.getSession();
            currentTransaction = currentSession.beginTransaction();
        }
        return currentSession;
    }

    public synchronized void closeCurrentSession(){
        if(currentTransaction.getStatus().equals(TransactionStatus.ACTIVE)) {
            currentTransaction.commit();
        }
    }

    @Override
    public Author save(Author entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void update(Author entity) {

    }

    @Override
    public Author findById(int id) {
        return null;
    }

    @Override
    public void delete(Author entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Author> findAll() {
        return null;
    }
}
