package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.ContactController;
import org.george.jylishop.controllers.ProductController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Yulya on 28.05.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController controller;

    @Mock
    private DataBase base;

    @Test
    public void totalList (){
        ArrayList<Product> catalogue = new ArrayList<Product>();
        Product p = new Product();
        catalogue.add(p);
        when(base.getCatalogue()).thenReturn(catalogue);

        ModelAndView view=controller.totalList();
        assertEquals(view.getViewName(),"total");
        assertNotNull(view.getModel().get("catalogue"));

        ArrayList list = (ArrayList)view.getModel().get("catalogue");
        assertEquals(list.size() , 1 );

    }

}
