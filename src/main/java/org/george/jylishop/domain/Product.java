package org.george.jylishop.domain;

/**
 * Created by Yulya on 21.05.2016.
 */
public class Product {
    private String description;
    private String title;
    private double price;
    private String picture;
    private int id;
    public final static String Gel_Type="opal_gel";
    public final static String Hemo_Type="hemostatic";

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
}
