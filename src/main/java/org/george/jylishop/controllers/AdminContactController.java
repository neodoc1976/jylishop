package org.george.jylishop.controllers;

import org.george.jylishop.db.ContactBase;
import org.george.jylishop.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yulya on 24.05.2016.
 */
@Controller
public class AdminContactController {
    @Autowired
    ContactBase base;

    @RequestMapping(value = "/admin/contact/update", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-update-contact");
        view.addObject("ci",base.getContact());
        return view;
    }

    @RequestMapping(value = "/admin/contact/update", method = RequestMethod.POST)
    public ModelAndView editForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String telephone,
            @RequestParam String location) {

        ModelAndView view = new ModelAndView("contact");

        Contact fresh=base.getContact();

        fresh.setName(name);
        fresh.setEmail(email);
        fresh.setAddress(address);
        fresh.setTelephone(telephone);
        fresh.setLocation(location);
        view.addObject("ci", fresh);

        return view;
    }

}

