package org.george.jylishop.dao;

import org.george.jylishop.domain.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*** Created by Yulya on 27.05.2016.
 */
@Component
public class ContactDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<Contact> getContact() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Contact", Contact.class).list();
    }

    @Transactional
    public Contact getContactById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Contact.class, id);
    }

    @Transactional
    public void updateContact(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.update(contact);
    }

    @Transactional
    public void deleteContact(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(contact);
    }

    @Transactional
    public void addContact(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.save(contact);
    }

}
