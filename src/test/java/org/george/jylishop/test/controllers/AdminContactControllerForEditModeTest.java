package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.AdminContactController;
import org.george.jylishop.db.ContactBase;
import org.george.jylishop.domain.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Yulya on 28.05.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminContactControllerForEditModeTest {
    @Spy
    ContactBase base;

    @InjectMocks
    AdminContactController controller;

    @Test
    public void editMethodTest(){
        String name= ("Name");
        String email=("Email");
        String address=("Address");
        String telephone=("Telephone");
        String location=("Location");

        ModelAndView view=controller.editForm(name,email,address,telephone,location);
        assertEquals(view.getViewName(),"contact");
        assertNotNull(view.getModel().get("ci"));

        Contact updated = base.getContact();
        assertEquals(updated.getName(),"Name");
        assertEquals(updated.getEmail(),"Email");
        assertEquals(updated.getAddress(),"Address");
        assertEquals(updated.getTelephone(),"Telephone");
        assertEquals(updated.getLocation(),"Location");

    }






}
