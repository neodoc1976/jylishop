package org.george.jylishop.domain;

import javax.persistence.*;

/**
 * Created by Yulya on 20.11.2016.
 */
@Entity
@Table
public class Money {
    @Id
    @GeneratedValue
    private int id;

    private String user_name;

    private double earnings;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }






}
