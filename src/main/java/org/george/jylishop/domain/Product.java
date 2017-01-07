package org.george.jylishop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 21.05.2016.
 */
@Entity
@Table
public class Product {

    private String description;
    private String title;
    private double price;
    private String picture;



    @Id
    @GeneratedValue
    private int id;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (id != product.id) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (!title.equals(product.title)) return false;
        if (picture != null ? !picture.equals(product.picture) : product.picture != null) return false;
        return manufacturer.equals(product.manufacturer);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = description != null ? description.hashCode() : 0;
        result = 31 * result + title.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + manufacturer.hashCode();
        return result;
    }

    @ManyToOne
    private Manufacturer manufacturer = new Manufacturer();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setQuantity(int quantity){ this.quantity = quantity;}

    public int getQuantity(){ return quantity;}

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }



}
