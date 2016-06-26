package org.george.jylishop.controllers;

import org.george.jylishop.db.ContactBase;
import org.george.jylishop.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/admin/contact/{id}/update", method = RequestMethod.GET)
    public ModelAndView getForm(@PathVariable int id) {
        ModelAndView view = new ModelAndView("admin-update-contact");
        view.addObject("ci",base.getContactById(id));
        return view;
    }

    @RequestMapping(value = "/admin/contact/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String telephone,
            @RequestParam String location) {

        ModelAndView view = new ModelAndView("contact");

        Contact fresh= base.getContactById(id);

        fresh.setName(name);
        fresh.setEmail(email);
        fresh.setAddress(address);
        fresh.setTelephone(telephone);
        fresh.setLocation(location);
        base.updateContact(fresh);
        view.addObject("ci", fresh);

        return view;
    }

    @RequestMapping("/admin/contact")
    public ModelAndView contactMethod() {
        ModelAndView model = new ModelAndView("admin-contact");
        model.addObject("ci", base.getContact());
        return model;


    }
    @RequestMapping(value = "/admin/contact/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-contact");
        Contact selectedContact = base.getContactById(id);

        if (selectedContact == null) {
            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "Sorry, the contact with the ID does not exist");
            return view;
        }

//
        base.deleteContact(selectedContact);
        delete.addObject("ci", base.getContact());
        return delete;


    }
    @RequestMapping(value = "/admin/contact/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-contact");
        return view;
    }

    @RequestMapping(value = "/admin/contact", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String address,
                                 @RequestParam String telephone,
                                 @RequestParam String location
                                )
    {
        ModelAndView post = new ModelAndView("admin-contact");
        Contact added = new Contact();
        post.addObject("ci", base.getContact());

        added.setName(name);
        added.setEmail(email);
        added.setAddress(address);
        added.setTelephone(telephone);
        added.setLocation(location);
        base.addContact(added);
        return post;
    }

}

