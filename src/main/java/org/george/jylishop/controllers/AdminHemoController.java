package org.george.jylishop.controllers;


import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.george.jylishop.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 21.05.2016.
 */
@Controller
public class AdminHemoController {
    @Autowired
    DataBase base;
    @Autowired
    ProductUtils utils;


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
                                 @RequestParam Integer id) {

        ModelAndView post = new ModelAndView("admin-total");
        Hemostatic newcomer = new Hemostatic();
        post.addObject("catalogue", base.getCatalogue());
        Product selectedProduct = utils.getProductById(id);

        if (selectedProduct != null) {

            ModelAndView error = new ModelAndView("error");
            error.addObject("message", "Product with this ID already exists, please fill out the form again.");
            return error;
        }
        newcomer.setId(id);
        newcomer.setTitle(title);
        newcomer.setDescription(description);
        newcomer.setVolume(volume);
        newcomer.setHemostaticSubstance(substance);
        newcomer.setPrice(price);
        List<Product> list = base.getCatalogue();
        list.add(newcomer);
        return post;

    }


    @RequestMapping(value = "/admin/hemos/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(@PathVariable int id,
                                 @RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam String hemostaticSubstance,
                                 @RequestParam String description) {

        ModelAndView post = new ModelAndView("admin-total");

        Product selectedProduct = utils.getProductById(id);

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
        post.addObject("catalogue", base.getCatalogue());
        return post;

    }

}



