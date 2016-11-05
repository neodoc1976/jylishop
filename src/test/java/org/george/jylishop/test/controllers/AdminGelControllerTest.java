package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.AdminGelController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.OpalescenseGel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Yulya on 29.05.2016.
 */
//@RunWith(MockitoJUnitRunner.class)
//public class AdminGelControllerTest {
//
//    @Mock
//    DataBase base;
//
//    @InjectMocks
//    AdminGelController gelController;
//
//    @Test
//    public void getFormTest() {
//
//        ModelAndView view = gelController.getForm();
//        assertEquals(view.getViewName(), "admin-add-gel");
//    }
//
//    @Test
//    public void postFormTest() {
//        String title = "Title";
//        double volume = 30.0;
//        double price = 50.0;
//        double reactantPercent = 10;
//        String description = "Description";
//        int id = 111;
//        String picture = "Picture";
//
//
////        when(base.getProductById(id)).thenReturn(null); // Ця логіка працювала коли base була Mock (мщмент "навчання")
//
////        int a= base.getCatalogue().size();
////
////        ModelAndView view = gelController.postForm(title, volume, price, reactantPercent, description, picture,0);
//
////        int b = base.getCatalogue().size();
//
////        OpalescenseGel product = (OpalescenseGel) base.getProductById(id);//Casting. Привів клас Product до Opalescence Gel
////
////         assertEquals(view.getViewName(), "admin-total");
////         assertNotNull(view.getModel().get("catalogue"));
////         assertEquals(product.getTitle(), title);
////        assertEquals(product.getVolume(),volume,0);
////        assertEquals(product.getPrice(), price, 0);
////        assertEquals(product.getReactantPercent(),reactantPercent,0);
////        assertEquals(product.getDescription(), description);
////        assertEquals(product.getPicture(), picture);
//  //      assertEquals(base.getCatalogue().size(),a+1);
//     }
//
//    @Test
//    public void postFormErrorTest() {
//        String title = "Title";
//        Double volume = 30.0;
//        Double price = 50.0;
//        double reactantPercent = 10;
//        String description = "Description";
//        int id = 101;//variables defined arbitrarily. variable value "ID" is a real
//        String picture = "Picture";
//
// //       assertEquals(base.getCatalogue().size(), 7);
//
//
//        OpalescenseGel product = new OpalescenseGel();
//        product.setTitle(title);
//        product.setVolume(volume);
//        product.setPrice(price);
//        product.setReactantPercent(reactantPercent);
//        product.setDescription(description);
//        product.setId(id);
//        product.setPicture(picture);
////        base.addProduct(product);
////
////        when(base.getProductById(id)).thenReturn(product);
////
////        ModelAndView view1 = gelController.postForm(title, volume, price,reactantPercent, description, picture,0);
////        assertEquals(view1.getViewName(), "error");
////        assertNotNull(view1.getModel().get("message"));
//    }
//
////      @Test
////    public void editFormTest() {
////        int id = 101;
////        String title = "Opalescense Gel PF";
////        String description = "Whitening gel for home whitening.";
////        double price = 5;
////        String picture = "first.jpg";
////        double reactantPercent = 10;
////        double volume = 1.2;
////
////        OpalescenseGel product = new OpalescenseGel();
////        product.setId(id);
////        product.setTitle(title);
////        product.setDescription(description);
////        product.setPrice(price);
////        product.setPicture(picture);
////        product.setReactantPercent(reactantPercent);
////        product.setVolume(volume);
////
////        when(base.getProductById(id)).thenReturn(product);
////
////        assertEquals(product.getId(), id);
////        assertEquals(product.getTitle(), title);
////        assertEquals(product.getDescription(), description);
////        assertEquals(product.getPrice(), price, 0);
////        assertEquals(product.getPicture(), picture);
////        assertEquals(product.getReactantPercent(), reactantPercent, 0);
////        assertEquals(product.getVolume(), volume, 0);
////
////        id = 101;
////        title = "Opalescence BOOST";
////        description = "Opalescence BOOST is a chemically activated power whitening gel ";
////        price = 5.45;
////        picture = "newpicture.jpg";
////        reactantPercent = 40;
////        volume = 0.4;
//
//
////        ModelAndView view = gelController.editForm(id,title,volume,price,reactantPercent,description,picture, 0);
////
////        assertEquals(view.getViewName(),"admin-total");
////        assertEquals(product.getTitle(),title);
////        assertEquals(product.getDescription(),description);
////        assertEquals(product.getPrice(),price,0);
////        assertEquals(product.getPicture(),picture);
////        assertEquals(product.getReactantPercent(),reactantPercent,0);
////        assertEquals(product.getVolume(),volume,0);
////        assertNotNull(view.getModel().get("catalogue"));
////    }
////    @Test
////    public void editFormError(){
////
////        int id=112;
////        String title = "Opalescense Gel PF";
////        String description = "Whitening gel for home whitening.";
////        double price = 5;
////        String picture = "first.jpg";
////        double reactantPercent = 10;
////        double volume = 1.2;
//
////        when(base.getProductById(id)).thenReturn(null);
////
////        ModelAndView view = gelController.editForm(id,title,volume,price,reactantPercent,description,picture,0);
////        assertEquals(view.getViewName(),"error");
////        assertNotNull(view.getModel().get("message"));
////
////    }
//
////}
//
//
//    }}