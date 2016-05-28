package org.george.jylishop.test.controllers;

import org.george.jylishop.controllers.ContactController;
import org.george.jylishop.db.ContactBase;
import org.george.jylishop.domain.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;


/**
 * Created by Yulya on 28.05.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
    @InjectMocks
    private ContactController controller;

    @Mock
    private ContactBase base;

    @Test
    public void contactTest(){
        Contact c= new Contact();
        c.setTelephone("test 066-381-86-14");
        c.setEmail("test neodoc3@gmail.com");
        c.setAddress("test Kiyv,Kasyana str,2,office 97");
        c.setName("test George Doc");
        c.setLocation("test https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2544.383883998672!2d30.454544915423373!3d50.378041979465436!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8fc4d5058a5%3A0x16341f27ce6cd8be!2z0LLRg9C7LiDQktCw0YHQuNC70Y8g0JrQsNGB0ZbRj9C90LAsIDIsINCa0LjRl9Cy!5e0!3m2!1suk!2sua!4v1464367094200");
        when(base.getContact()).thenReturn(c);

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
