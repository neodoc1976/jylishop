package org.george.jylishop.exceptoins;

/**
 * Created by Yulya on 28.01.2017.
 */
public class ManufacturerIsNotFoundException extends JuliShopException {

    private int manufacturerId;

    public int getManufacturerId() {
        return manufacturerId;
    }

    public ManufacturerIsNotFoundException(int id){
        manufacturerId=id;
    }

}
