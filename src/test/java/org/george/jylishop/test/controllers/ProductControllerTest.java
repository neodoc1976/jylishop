package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.ProductController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    public void totalList() {
        ArrayList<Product> catalogue = new ArrayList<Product>();
        Product p = new Product();
        catalogue.add(p);
        when(base.getCatalogue()).thenReturn(catalogue);

        ModelAndView view = controller.totalList("sort");
        assertEquals(view.getViewName(), "total");
        assertNotNull(view.getModel().get("catalogue"));

        ArrayList list = (ArrayList) view.getModel().get("catalogue");
        assertEquals(list.size(), 1);
    }

    @Test
    public void getProductOpalescenceGel() {

        when(base.getProductById(1)).thenReturn(new OpalescenseGel());

        ModelAndView view = controller.getProduct(1);
        assertEquals(view.getViewName(), "gel-product");
        assertNotNull(view.getModel().get("opalescenseInfo"));
        assertTrue(view.getModel().get("opalescenseInfo")instanceof OpalescenseGel);
    }
    @Test
    public void getProductHemostatic(){
        when(base.getProductById(2)).thenReturn(new Hemostatic());

        ModelAndView view = controller.getProduct(2);
        assertEquals(view.getViewName(),"hemo-product");
        assertNotNull(view.getModel().get("hemoInfo"));
        assertTrue(view.getModel().get("hemoInfo")instanceof Hemostatic);

    }
    @Test
    public void getProdustErrorID (){
        when(base.getProductById(3)).thenReturn(null);

        ModelAndView view=controller.getProduct(3);
        assertEquals(view.getViewName(),"error");
        assertNotNull(view.getModel().get("message"));

    }

}
