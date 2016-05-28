package org.george.jylishop.controllers;

import org.george.jylishop.db.ContactBase;
import org.george.jylishop.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yulya on 02.05.2016.
 */
@Controller
public class ContactController {
    @Autowired
    ContactBase base;

    @RequestMapping("/contact")
    public ModelAndView contactMethod() {
        ModelAndView model = new ModelAndView("contact");
        model.addObject("ci",base.getContact());
        return model;

    }


}
