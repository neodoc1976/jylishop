package org.george.jylishop.dao;

import org.george.jylishop.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yulya on 06.11.2016.
 */
@Component
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public User getUserInfo (String username){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,username);
    }

    @Transactional
    public void storeNewUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }


}
