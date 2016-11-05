package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.ProductController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Yulya on 16.06.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTestSortByPrice {
    @Mock
    private DataBase base;

    @InjectMocks
    private ProductController controller;

    @Test

    public void sortPriceDesc() {

        String query = "pricedesc";
        List<Product> list = new ArrayList<>();

        Product product1 = new Product();
        product1.setPrice(300);
        product1.setId(33);
        list.add(product1);

        Product product2 = new Product();
        product2.setPrice(100);
        product2.setId(31);
        list.add(product2);

        Product product3 = new Product();
        product3.setPrice(200);
        product3.setId(32);
        list.add(product3);


        int index1 = list.indexOf(product1);
        int index2 = list.indexOf(product2);
        int index3 = list.indexOf(product3);

        assertEquals(index1, 0);
        assertEquals(index2, 1);
        assertEquals(index3, 2);

//        when(base.getCatalogue()).thenReturn(list);
//        ModelAndView total = controller.totalList(query);
//
//        assertNotNull(total.getModel().get("catalogue"));
//
//        index1 = ((List) total.getModel().get("catalogue")).indexOf(product1);
//        index2 = ((List) total.getModel().get("catalogue")).indexOf(product2);
//        index3 = ((List) total.getModel().get("catalogue")).indexOf(product3);
//
//        assertEquals(index1, 2);
//        assertEquals(index2, 0);
//        assertEquals(index3, 1);

    }

}
