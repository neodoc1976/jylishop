package org.george.jylishop.domain;

/**
 * Created by Yulya on 21.05.2016.
 */
public class Product {
//        public final static String GEL_TYPE = "opal_gel";
//    public final static String HEMO_TYPE = "hemostatic";

    public static enum Type {
        GEL("opal_gel"), HEMO("hemostatic");

        String dataBaseString;

        Type(String dataBaseString) {
            this.dataBaseString = dataBaseString;
        }

        public String getDataBaseString() {
            return dataBaseString;
        }
    }


    private String description;
    private String title;
    private double price;
    private String picture;
    private int id;
    private int quantity;
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
