package org.george.jylishop.dao;

import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.exceptoins.ManufacturerIsNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Yulya on 03.11.2016.
 */
@Component
public class ManufacturerDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addManufacturer(Manufacturer manufacturer){
        Session session = sessionFactory.getCurrentSession();
        session.save(manufacturer);
    }

    @Transactional
    public void updateManufacturer(Manufacturer manufacturer){
        Session session = sessionFactory.getCurrentSession();
        session.update(manufacturer);
    }

    @Transactional
    public void deleteManufacturer(Manufacturer manufacturer){
        Session session = sessionFactory.getCurrentSession();
        session.delete(manufacturer);

    }

    @Transactional
    public Manufacturer getManufacturerById(int id){
        Session session = sessionFactory.getCurrentSession();
        Manufacturer manufacturer = new Manufacturer();
        manufacturer=session.get(Manufacturer.class, id);
        if (manufacturer == null){
            throw new ManufacturerIsNotFoundException(id);
        }
        return manufacturer;

    }

    @Transactional
    public List<Manufacturer> getAllManufacturers(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Manufacturer",Manufacturer.class).list();
    }

}
