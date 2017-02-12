package org.george.jylishop.domain;

import lombok.Data;


import javax.persistence.*;

/**
 * Created by Yulya on 28.01.2017.
 */

@Data
@Entity
@Table (name = "comment_vote")
public class CommentVote {


    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    Comment comment;

    @ManyToOne
    User user;

    boolean rating;

}
