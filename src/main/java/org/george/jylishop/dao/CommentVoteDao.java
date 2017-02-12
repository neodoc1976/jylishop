package org.george.jylishop.dao;

import org.george.jylishop.domain.Comment;
import org.george.jylishop.domain.CommentVote;
import org.george.jylishop.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yulya on 28.01.2017.
 */
@Component
public class CommentVoteDao {
    @Autowired
    SessionFactory sessionFactory;

//    @Transactional
//    public List<CommentVote> getVoteByComment(Comment comment) {
//        Session session = sessionFactory.getCurrentSession();
//        return session
//                .createQuery("from CommentVote where comment=:comment_id", CommentVote.class)
//                .setParameter("comment", comment)
//                .list();
//    }

    @Transactional
    public void updateVoteOnComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.update(comment);
    }
    @Transactional
    public void addVoteOnComment (CommentVote commentVote){
        Session session = sessionFactory.getCurrentSession();
        session.save(commentVote);
    }



}