package org.george.jylishop.controllers;

import org.george.jylishop.dao.ContactDao;
import org.george.jylishop.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

/**
 * Created by Yulya on 24.05.2016.
 */
@Controller
public class AdminContactController {
    @Autowired
    ContactDao base;

    @RequestMapping(value = "/admin/contact/{id}/update", method = RequestMethod.GET)
    public ModelAndView getForm(@PathVariable int id) {
        ModelAndView view = new ModelAndView("admin-update-contact");
        view.addObject("ci", base.getContactById(id));
        return view;
    }

    @RequestMapping(value = "/admin/contact/{id}/update", method = RequestMethod.POST)
    public String editForm(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String telephone,
            @RequestParam String location) {

        Contact fresh = base.getContactById(id);

        fresh.setName(name);
        fresh.setEmail(email);
        fresh.setAddress(address);
        fresh.setTelephone(telephone);
        fresh.setLocation(location);
        base.updateContact(fresh);
        return "redirect:/admin/contact";
    }

    @RequestMapping(value = "/admin/contact", method = RequestMethod.GET)
    public ModelAndView contactMethod() {
        ModelAndView model = new ModelAndView("admin-contact");
        model.addObject("ci", base.getContact());
        return model;


    }

    @RequestMapping(value = "/admin/contact/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        Contact selectedContact = base.getContactById(id);
        base.deleteContact(selectedContact);
        return new ModelAndView("redirect:/admin/contact");


    }

    @RequestMapping(value = "/admin/contact/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-contact");
        return view;
    }

    @RequestMapping(value = "/admin/contact", method = RequestMethod.POST)
    public String postForm(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String address,
                           @RequestParam String telephone,
                           @RequestParam String location
    ) {

        Contact added = new Contact();


        added.setName(name);
        added.setEmail(email);
        added.setAddress(address);
        added.setTelephone(telephone);
        added.setLocation(location);


        base.addContact(added);
        return "redirect:/admin/contact";
    }

}

