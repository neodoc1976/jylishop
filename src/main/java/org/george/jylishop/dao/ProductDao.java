package org.george.jylishop.dao;

import org.george.jylishop.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Yulya on 05.11.2016.
 */
@Component
public class ProductDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<Product> getCatalogue(){
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("from Product",Product.class).list();
        return null;
    }
    

}
