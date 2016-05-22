package org.george.jylishop.utils;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Yulya on 22.05.2016.
 */
@Component
public class ProductUtils {
    @Autowired
    DataBase base;

    public Product getProductById(int id) {
        Product selectedProduct = null;
        for (Product p : base.getCatalogue()) {
            if (p.getId() == id) {
                selectedProduct = p;
            }
        }

        return selectedProduct;
    }

}
