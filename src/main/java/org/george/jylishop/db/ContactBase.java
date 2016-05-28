package org.george.jylishop.db;

import org.george.jylishop.domain.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Yulya on 27.05.2016.
 */
@Component
public class ContactBase {
    Contact contact;

    public Contact getContact() {
        return contact;
    }

    public ContactBase (){
        contact = new Contact();
        contact.setName("George Doc");
        contact.setEmail("neodoc3@gmail.com");
        contact.setAddress("Kiyv,Kasyana str,2,office 97");
        contact.setTelephone("066-381-86-14");
        contact.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2544.383883998672!2d30.454544915423373!3d50.378041979465436!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8fc4d5058a5%3A0x16341f27ce6cd8be!2z0LLRg9C7LiDQktCw0YHQuNC70Y8g0JrQsNGB0ZbRj9C90LAsIDIsINCa0LjRl9Cy!5e0!3m2!1suk!2sua!4v1464367094200");
    }

}
