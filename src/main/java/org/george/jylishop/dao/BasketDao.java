package org.george.jylishop.dao;

import org.george.jylishop.domain.Basket;
import org.george.jylishop.domain.Product;
import org.george.jylishop.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yulya on 12.11.2016.
 */
@Component
public class BasketDao {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ProductDao productDao;

    @Transactional
    public Basket getUserBasket(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("from Basket where user.username=:username", Basket.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Transactional
    public void addBasket(Basket basket) {
        Session session = sessionFactory.getCurrentSession();
        session.save(basket);
    }

    @Transactional
    public void updateBasket(Basket basket) {
        Session session = sessionFactory.getCurrentSession();
        session.update(basket);

    }



}
