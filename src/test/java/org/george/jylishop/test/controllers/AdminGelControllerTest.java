package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.AdminGelController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.george.jylishop.utils.ProductUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Yulya on 29.05.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminGelControllerTest {

    @Spy
    DataBase base;

    @Mock
    ProductUtils utils;

    @InjectMocks
    AdminGelController gelController;

    @Test
    public void getFormTest() {

        ModelAndView view = gelController.getForm();
        assertEquals(view.getViewName(), "admin-add-gel");
    }

    @Test
    public void postFormTest() {
        String title = "Title";
        Double volume = 30.0;
        Double price = 50.0;
        Integer reactantPercent = 10;
        String description = "Description";
        int id = 111;
        String picture = "Picture";

        base.getCatalogue().clear();
        assertEquals(base.getCatalogue().size(), 0);

        when(utils.getProductById(111)).thenReturn(null);
        ModelAndView view = gelController.postForm(title, volume, price, reactantPercent, description, id, picture);
        assertEquals(view.getViewName(), "admin-total");
        assertEquals(base.getCatalogue().get(0).getTitle(), title); // Не знаю як тут привести до класу OpalescenceGel.
        assertEquals(base.getCatalogue().get(0).getPrice(), price, 0);// Отриманий catalogue є об"єктом загального класу Product
        assertEquals(base.getCatalogue().get(0).getDescription(), description);//Не можу знайти доступу до змінних reactantPercent та volume
        assertEquals(base.getCatalogue().get(0).getPicture(), picture);
        assertEquals(base.getCatalogue().get(0).getId(), id);
        assertEquals(base.getCatalogue().size(), 1);
    }

    @Test
    public void postFormErrorTest() {
        String title = "Title";
        Double volume = 30.0;
        Double price = 50.0;
        Integer reactantPercent = 10;
        String description = "Description";
        int id = 101;//variables defined arbitrarily. variable value "ID" is a real
        String picture = "Picture";

        assertEquals(base.getCatalogue().size(), 7);
        Product product = base.getCatalogue().get(0);
        when(utils.getProductById(101)).thenReturn(product);
        ModelAndView view1 = gelController.postForm(title, volume, price, reactantPercent, description, id, picture);
        assertEquals(view1.getViewName(), "error");
    }

}



