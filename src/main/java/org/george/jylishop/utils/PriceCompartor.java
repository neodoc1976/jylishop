package org.george.jylishop.utils;

import org.george.jylishop.domain.Product;

import java.util.Comparator;

/**
 * Created by Yulya on 05.06.2016.
 */
public class PriceCompartor implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2) {


        if (product1.getPrice() < product2.getPrice()) {
            return 1;

        } else if (product1.getPrice() > product2.getPrice()) {
            return -1;

        } else {
            return 0;

        }
    }
}
