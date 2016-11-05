package org.george.jylishop.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Yulya on 02.05.2016.
 */
@Entity
@Table
public class OpalescenseGel extends Product {
    private double reactantPercent;
    private double volume;

    public double getReactantPercent() {
        return reactantPercent;
    }

    public void setReactantPercent(double reactantPercent) {
        this.reactantPercent = reactantPercent;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
