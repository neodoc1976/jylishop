package org.george.jylishop.domain;

import lombok.Data;

/**
 * Created by Yulya on 28.01.2017.
 */

@Data
public class CommentVote {

    Comment comment;
    User user;
    Product product;
    boolean positive = true;
    boolean negative = false;



}
