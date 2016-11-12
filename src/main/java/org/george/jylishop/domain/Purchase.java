package org.george.jylishop.domain;

import javax.persistence.*;

/**
 * Created by Yulya on 09.11.2016.
 */
@Entity
@Table
public class Purchase {


    @Id
    @GeneratedValue
    int id;

    @OneToOne
    private Product product;

    private int prodCountForUser;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProdCountForUser() {
        return prodCountForUser;
    }

    public void setProdCountForUser(int prodCountForUser) {
        this.prodCountForUser = prodCountForUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
