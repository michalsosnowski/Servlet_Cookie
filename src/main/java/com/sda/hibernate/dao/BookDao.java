package com.sda.hibernate.dao;

import com.sda.hibernate.config.HibernateUtils;
import com.sda.hibernate.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDao implements DaoInterface<Book> {

    private Session currentSession;
    private Transaction currentTransaction;

    public BookDao(){

    }

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
        currentTransaction.commit();
    }


    @Override
    public Book save(Book entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Book> findAll() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);

        return getCurrentSession().createQuery(criteriaQuery).getResultList();

    }
}
