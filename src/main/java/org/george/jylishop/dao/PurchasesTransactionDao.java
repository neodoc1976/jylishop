package org.george.jylishop.dao;

import org.george.jylishop.domain.Product;
import org.george.jylishop.domain.PurchaseTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yulya on 20.11.2016.
 */
@Component
public class PurchasesTransactionDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addNewEarnings(PurchaseTransaction purchaseTransaction) {
        Session session = sessionFactory.getCurrentSession();
        session.save(purchaseTransaction);
    }

    @Transactional
    public List<PurchaseTransaction> getPurchasesHistory() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PurchaseTransaction", PurchaseTransaction.class).list();
    }

    @Transactional
    public List<PurchaseTransaction> getUserPurchasesHistory(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("from PurchaseTransaction  where user.username = :username", PurchaseTransaction.class)
                .setParameter("username", username)
                .list();
    }

    @Transactional
    public PurchaseTransaction getUserInvoiceById(int id) {
        Session session = sessionFactory.getCurrentSession();
       return session.get(PurchaseTransaction.class, id);

    }




}