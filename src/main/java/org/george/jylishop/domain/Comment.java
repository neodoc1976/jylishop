package org.george.jylishop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 20.12.2016.
 */
@Data
@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue
    private int id;
    private String date;
    private String userName;
    @ManyToOne
    private Product product;
    private String message;
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
    public List<CommentVote> commentVote;


    public boolean isUserVoted(String userName) {
        for (CommentVote cv : commentVote) {
            if (cv.getUser().getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public int getPositiveRating() {
        int sum = 0;
        for (CommentVote cv : commentVote) {
            if (cv.rating == Rating.POSITIVE) {
                sum++;
            }
        }
        return sum;
    }

    public int getNegativeRating() {
        int sum = 0;
        for (CommentVote cv : commentVote) {
            if (cv.rating == Rating.NEGATIVE) {
                sum++;
            }
        }
        return sum;
    }

    public int getWorthlessRating() {
        int sum = 0;
        for (CommentVote cv : commentVote) {
            if (cv.rating == Rating.WORTHLESS) {
                sum++;
            }
        }
        return sum;
    }

}