package org.george.jylishop.controllers;

import org.george.jylishop.dao.CommentDao;
import org.george.jylishop.dao.CommentVoteDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.dao.UserDao;
import org.george.jylishop.domain.Comment;
import org.george.jylishop.domain.CommentVote;
import org.george.jylishop.domain.Product;
import org.george.jylishop.domain.Rating;
import org.george.jylishop.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yulya on 28.01.2017.
 */
@Controller

public class CommentController {
    @Autowired
    CommentVoteDao commentVoteDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;


    @RequestMapping({""})
    public ModelAndView showVote() {


        return null;
    }

    @RequestMapping(value = "/product/{id}/comment", method = RequestMethod.POST)
    public ModelAndView addComment(@PathVariable int id,
                                   @RequestParam String message) {
        if (message.isEmpty()) {

            return new ModelAndView("redirect:/products/{id}");
        }
        Comment comment = new Comment();
        Product selectedProduct = productDao.getProductById(id);
        comment.setProduct(selectedProduct);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy ");
        Date date = new Date();
        comment.setDate(dateFormat.format(date));
        comment.setMessage(message);
        comment.setUserName(SecurityUtils.getCurrentUsername());
        commentDao.addComment(comment);
        return new ModelAndView("redirect:/products/{id}");
    }

    @RequestMapping("/product/comment/{comment_id}/{rating}")
    public ModelAndView ratingForComment
            (@PathVariable int comment_id,
             @PathVariable String rating) {

        String currentUsername = SecurityUtils.getCurrentUsername();
        Comment comment = commentDao.getCommentById(comment_id);
        if (comment.isUserVoted(currentUsername)) {
            return new ModelAndView("redirect:/products/" + comment.getProduct().getId());
        }
        CommentVote commentVote = new CommentVote();
        commentVote.setComment(comment);
        commentVote.setUser(userDao.getUserInfo(currentUsername));
        if (rating.equals("positive") == true) {
            commentVote.setRating(Rating.POSITIVE);
        }else{
            commentVote.setRating(Rating.NEGATIVE);
        }

        commentVoteDao.addVoteOnComment(commentVote);
        return new ModelAndView("redirect:/products/" + comment.getProduct().getId());
    }

}
