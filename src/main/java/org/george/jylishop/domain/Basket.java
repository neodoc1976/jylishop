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
    private int id;

    @OneToOne
    User user;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Product> purchases = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Product> purchases) {
        this.purchases = purchases;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}