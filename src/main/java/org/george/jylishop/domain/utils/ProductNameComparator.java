package org.george.jylishop.domain.utils;

import org.george.jylishop.domain.Comment;
import org.george.jylishop.domain.Product;

import java.util.Comparator;

/**
 * Created by Yulya on 20.11.2016.
 */
public class ProductNameComparator implements Comparator<Product> {


    @Override
    public int compare(Product product1, Product product2) {

        return product1.getTitle().compareTo(product2.getTitle());

    }




}
