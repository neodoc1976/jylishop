package org.george.jylishop.domain;

import javax.persistence.*;

/**
 * Created by Yulya on 20.12.2016.
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
