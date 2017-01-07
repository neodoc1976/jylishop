package org.george.jylishop.dao;

import org.george.jylishop.domain.Comment;
import org.george.jylishop.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yulya on 20.12.2016.
 */
@Component
public class CommentDao {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(comment);
    }

    @Transactional
    public void deleteComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(comment);
    }

    @Transactional
    public List<Comment> getCommentByProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        return session
                .createQuery("from Comment where product=:product ", Comment.class)
                .setParameter("product", product)
                .list();
    }

    @Transactional
    public Comment getCommentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Comment where id=:id", Comment.class)
                .setParameter("id", id)
                .uniqueResult();
    }

//    @Transactional
//    public void deleteCommentById(int id){
//        Session session = sessionFactory.getCurrentSession();
//        session.createQuery("delete from Comment where comment.id=:id",Comment.class).setParameter("id",id);
//    }

    @Transactional
    public List<Comment> getAllComment() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Comment", Comment.class).list();
    }
}
