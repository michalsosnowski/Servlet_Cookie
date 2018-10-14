package com.sda.hibernate.dao;

import com.sda.hibernate.config.HibernateUtils;
import com.sda.hibernate.entity.Book;
import com.sda.hibernate.entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoryDao implements DaoInterface<Category>{

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
    public Category save(Category entity) {
        try {
            getCurrentSession().save(entity);
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Category entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Category findById(int id) {
        //TODO zabezpieczenie przed nullpointer
        Category category = getCurrentSession().get(Category.class, id);
        return category;
    }

    @Override
    public void delete(Category entity) {
        //TODO zabezpieczenie przed usuwaniem rekordu ktory nie istnieje
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
//        String sql = "delete from Category";
//        Query query = getCurrentSession().createNativeQuery(sql);
//        query.executeUpdate();
        List<Category> entityList = findAll();
        for (Category entity : entityList) {
            delete(entity);
        }

    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = getCurrentSession().createQuery(
                "FROM " + Category.class.getName()
        ).list();
        return categoryList;
    }

    public List<Category> findByName(String name){

        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        Query<Category> categoryQuery = getCurrentSession().createQuery(criteriaQuery);

        return categoryQuery.getResultList();
    }


}
