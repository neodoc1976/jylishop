package org.george.jylishop.controllers;


import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Yulya on 21.05.2016.
 */
@Controller
public class AdminHemoController {
    @Autowired
    DataBase base;


    @RequestMapping(value = "/admin/hemos/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-hemo");
        return view;
    }

    @RequestMapping(value = "/admin/hemos", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam String substance,
                                 @RequestParam String description,
                                 @RequestParam String picture) {

        ModelAndView post = new ModelAndView("admin-total");
        Hemostatic newcomer = new Hemostatic();
        post.addObject("catalogue", base.getCatalogue());

        newcomer.setTitle(title);
        newcomer.setDescription(description);
        newcomer.setVolume(volume);
        newcomer.setHemostaticSubstance(substance);
        newcomer.setPrice(price);
        newcomer.setPicture(picture);
        base.addProduct(newcomer);
        return post;

    }


    @RequestMapping(value = "/admin/hemos/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(@PathVariable int id,
                                 @RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam String hemostaticSubstance,
                                 @RequestParam String description,
                                 @RequestParam String picture) {

        ModelAndView post = new ModelAndView("admin-total");
        Product selectedProduct = base.getProductById(id);

        if (selectedProduct == null) {
            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "Sorry, the product with the ID does not exist");
            return view;
        }

        Hemostatic updated = (Hemostatic) selectedProduct;// Casting
        updated.setTitle(title);
        updated.setDescription(description);
        updated.setVolume(volume);
        updated.setHemostaticSubstance(hemostaticSubstance);
        updated.setPrice(price);
        updated.setPicture(picture);
        base.updatePtroduct(updated);
        post.addObject("catalogue", base.getCatalogue());
        return post;
    }

}



