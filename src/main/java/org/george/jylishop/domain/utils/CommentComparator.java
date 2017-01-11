package org.george.jylishop.domain.utils;

import org.george.jylishop.domain.Comment;

import java.util.Comparator;

/**
 * Created by Yulya on 11.01.2017.
 */
public class CommentComparator implements Comparator<Comment> {

    @Override

    public int compare (Comment comment1,Comment comment2){
        return comment1.getDate().compareTo(comment2.getDate());
    }
}
