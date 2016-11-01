package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.ContactController;
import org.george.jylishop.dao.ContactDao;
import org.george.jylishop.domain.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertEquals;


/**
 * Created by Yulya on 28.05.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
    @InjectMocks
    private ContactController controller;

    @Mock
    private ContactDao base;

    @Test
    public void contactTest(){
               Contact c= new Contact();
        c.setTelephone("test 066-381-86-14");
        c.setEmail("test neodoc3@gmail.com");
        c.setAddress("test Kiyv,Kasyana str,2,office 97");
        c.setName("test George Doc");

        ModelAndView view=controller.contactMethod();
        assertEquals(view.getViewName(), "contact");
        assertNotNull(view.getModel().get("ci"));

        Contact returnedContact = (Contact) view.getModel().get("ci");

        assertEquals(c.getTelephone(),returnedContact.getTelephone());
        assertEquals(c.getEmail(),returnedContact.getEmail());
        assertEquals(c.getAddress(),returnedContact.getAddress());
        assertEquals(c.getName(),returnedContact.getName());
        assertEquals(c.getLocation(),returnedContact.getLocation());

    }


}
