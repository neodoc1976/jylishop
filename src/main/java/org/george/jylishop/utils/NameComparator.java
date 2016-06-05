package org.george.jylishop.utils;

import org.george.jylishop.domain.Product;

import java.util.Comparator;

/**
 * Created by Yulya on 05.06.2016.
 */
public class NameComparator implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2) {

        return product1.getTitle().compareTo(product2.getTitle());

    }

}