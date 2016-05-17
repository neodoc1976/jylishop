package org.george.jylishop.domain;

/**
 * Created by Yulya on 16.05.2016.
 */
public class Hemostatic {
    private String title;
    private String hemostaticSubstance;
    private String description;
    private double volume;
    private double price;

    private String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String getHemostaticSubstance() {
        return hemostaticSubstance;
    }

    public void setHemostaticSubstance(String hemostaticSubstance) {
        this.hemostaticSubstance = hemostaticSubstance;
    }

    private String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    private double getVolume(){
        return volume;
    }

    public void setVolume(double volume){
        this.volume=volume;
    }

    private double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }


}

