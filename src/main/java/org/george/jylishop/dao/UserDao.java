package org.george.jylishop.dao;

import org.george.jylishop.domain.User;
import org.george.jylishop.domain.UserRole;
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
    @Transactional
    public void updateCurrentUserInfo (User user){
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }
    @Transactional
    public List<UserRole> getUserRolesAsList (String username){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from UserRole where username = :username ", UserRole.class).
                setParameter("username", username).
                list();
    }

}
