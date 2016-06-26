package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.AdminHemoController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Hemostatic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Yulya on 30.05.2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class AdminHemoControllerTest {
    @Spy
    DataBase base;

    @InjectMocks
    AdminHemoController hemoController;

    @Test
    public void getFormTest() {

        ModelAndView view = hemoController.getForm();
        assertEquals(view.getViewName(), "admin-add-hemo");
    }

    @Test
    public void postFormTest() {
        String title = "Title";
        double volume = 30.0;
        double price = 50.0;
        String substance ="Hemostatic Substance";
        String description = "Description";
        int id = 444;
        String picture = "Picture";
        int a=base.getCatalogue().size();

        ModelAndView view = hemoController.postForm(title,volume, price,substance,description, id, picture);
        Hemostatic product = (Hemostatic) base.getProductById(id);

        assertEquals(view.getViewName(), "admin-total");
        assertNotNull(view.getModel().get("catalogue"));
        assertEquals(product.getTitle(), title);
        assertEquals(product.getVolume(),volume,0);
        assertEquals(product.getPrice(), price, 0);
        assertEquals(product.getHemostaticSubstance(),substance);
        assertEquals(product.getDescription(), description);
        assertEquals(product.getPicture(), picture);
        assertEquals(product.getId(), id);
        assertEquals(base.getCatalogue().size(),a+1);
    }

    @Test
    public void postFormErrorTest() {
        String title = "Title for Hemo";
        Double volume = .45;
        Double price = 7.62;
        String substance = "Cherry flavored antacids";
        String description = "Description";
        int id = 404;
        String picture = "Picture";

        assertEquals(base.getCatalogue().size(), 7);

        Hemostatic product = (Hemostatic) base.getProductById(id);

        when(base.getProductById(id)).thenReturn(product);

        ModelAndView view = hemoController.postForm(title, volume, price,substance, description, id, picture);
        assertEquals(view.getViewName(), "error");
        assertNotNull(view.getModel().get("message"));
    }

    @Test
    public void editFormTest() {
        int id = 404;
        String title = "ViscoStat";
        String description = "ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle. ";
        double price = 4.8;
        String picture = "visco.jpg";
        String hemostaticSubstance = "Ferric Sulphate" ;
        double volume = 1.2;

        Hemostatic product = (Hemostatic) base.getProductById(id);
        assertEquals(product.getId(),id);
        assertEquals(product.getVolume(),volume,0);
        assertEquals(product.getTitle(), title);
        assertEquals(product.getDescription(), description);
        assertEquals(product.getPrice(),price,0);
        assertEquals(product.getPicture(),picture);
        assertEquals(product.getHemostaticSubstance(),hemostaticSubstance);


        id = 404;
        title = "Astringedent x";
        description = "An aqueous, 12.7% iron solution, Astringedent X contains equivalent ferric sulfate and ferric subsulfate with a pH of ~1.0.";
        price = .308;
        picture = "astringedent.jpg";
        hemostaticSubstance="12.7% iron solution";
        volume = 30;

        when(base.getProductById(id)).thenReturn(product);

        ModelAndView view = hemoController.editForm(id,title,volume,price,hemostaticSubstance,description,picture);

        assertEquals(view.getViewName(),"admin-total");
        assertEquals(product.getTitle(),title);
        assertEquals(product.getDescription(),description);
        assertEquals(product.getPrice(),price,0);
        assertEquals(product.getPicture(),picture);
        assertEquals(product.getHemostaticSubstance(),hemostaticSubstance);
        assertEquals(product.getVolume(),volume,0);
        assertNotNull(view.getModel().get("catalogue"));
    }
    @Test
    public void editFormError(){

        int id=444;
        String title="Title" ;
        String description="Description" ;
        double price=6.35 ;
        String picture ="image.jpeg";
        String hemostaticSubstance = "substance";
        double volume = 1.2;

        when(base.getProductById(id)).thenReturn(null);

        ModelAndView view = hemoController.editForm(id,title,volume,price,hemostaticSubstance,description,picture);
        assertEquals(view.getViewName(),"error");
        assertNotNull(view.getModel().get("message"));

    }

}



