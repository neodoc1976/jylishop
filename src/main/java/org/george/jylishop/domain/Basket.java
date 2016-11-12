package org.george.jylishop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 09.11.2016.
 */
@Entity
@Table
public class Basket {


    @Id
    @GeneratedValue
    int id;


    @OneToOne
    User user;

    @OneToMany
    List<Purchase> purchases = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}