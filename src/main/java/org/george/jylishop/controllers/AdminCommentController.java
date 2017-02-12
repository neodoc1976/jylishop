package org.george.jylishop.controllers;

import org.george.jylishop.dao.CommentDao;
import org.george.jylishop.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Yulya on 07.01.2017.
 */
@Controller
public class AdminCommentController {
    @Autowired
    CommentDao commentDao;

    @RequestMapping(value="/admin/comments/{id}/delete",method = RequestMethod.GET)
    public ModelAndView deleteComment(@PathVariable int id){
        commentDao.getCommentById(id);
        commentDao.deleteComment(commentDao.getCommentById(id));
        return new ModelAndView("redirect:/admin/comments");
    }
    @RequestMapping("/admin/comments")
    public ModelAndView seeAllComments() {
        ModelAndView view = new ModelAndView("admin-comments");
        List<Comment> comments = commentDao.getAllComment();
        view.addObject("comments", comments);
        return view;
    }
}
