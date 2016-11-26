package org.george.jylishop.dao;

import org.george.jylishop.domain.Money;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yulya on 20.11.2016.
 */
@Component
public class MoneyDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addNewEarnings(Money money) {
        Session session = sessionFactory.getCurrentSession();
        session.save(money);
    }

    @Transactional
    public List<Money> getPurchasesHistory() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Money", Money.class).list();
    }
    @Transactional
    public List<Money> getUserPurchasesHistory(String username){
        Session session = sessionFactory.getCurrentSession();
         return session
                .createQuery("from Money where user_name= :username", Money.class)
                .setParameter("username",username)
                .list();
    }
 }