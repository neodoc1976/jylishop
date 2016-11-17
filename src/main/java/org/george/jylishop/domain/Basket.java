package org.george.jylishop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public int productInBasketCount(List<Product> purchases, int id) {

        int product_count = 0;
        for (Product p : purchases
                ) {
            int product_id = p.getId();
            if (product_id == id) {
                product_count = product_count + 1;
            }

        }
        return product_count;
    }




}