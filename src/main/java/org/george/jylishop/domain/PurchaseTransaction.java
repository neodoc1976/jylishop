package org.george.jylishop.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yulya on 20.11.2016.
 */
@Entity
@Table
public class PurchaseTransaction {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User user;

    private double earnings;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> purchases;

    private String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Product> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Product> purchases) {
        this.purchases = purchases;
    }

    public Map<Product,Integer> getGroupedProduct(List <Product> purchases){
        Map<Product, Integer> map = new HashMap<>();
        for (Product p : purchases) {
            Integer count = map.get(p);
            if (count != null) {
                count = count + 1;
                map.put(p, count);
            } else {
                map.put(p, 1);
            }
        }
        return  map;
    }


}
