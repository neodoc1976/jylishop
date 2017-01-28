package org.george.jylishop.exceptoins;

import javax.persistence.JoinColumn;

/**
 * Created by Yulya on 28.01.2017.
 */
public class ProductIsNotFoundException extends JuliShopException {

    private int productId;

    public int getProductId() {
        return productId;
    }
    public ProductIsNotFoundException(int id) {
        productId = id;
    }

}
