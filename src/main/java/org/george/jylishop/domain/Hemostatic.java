package org.george.jylishop.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yulya on 16.05.2016.
 *
 */
@Entity
@Table
public class Hemostatic extends Product {
    private String hemostaticSubstance;
    private double volume;

    public String getHemostaticSubstance() {
        return hemostaticSubstance;
    }

    public void setHemostaticSubstance(String hemostaticSubstance) {
        this.hemostaticSubstance = hemostaticSubstance;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }


}

