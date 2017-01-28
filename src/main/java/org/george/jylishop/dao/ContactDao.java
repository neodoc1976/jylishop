package org.george.jylishop.dao;

import org.george.jylishop.domain.Contact;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.exceptoins.ContactIsNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
        Contact contact = session.get(Contact.class, id);
        if(contact==null){
            throw new ContactIsNotFoundException();
        }
        return contact;
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
