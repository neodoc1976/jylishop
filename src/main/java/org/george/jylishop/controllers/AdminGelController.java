package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yulya on 03.05.2016.
 */
@Controller
public class AdminGelController {
    @Autowired
    DataBase base;

    @RequestMapping(value = "/admin/gels/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-gel");
        return view;
    }

    @RequestMapping(value = "/admin/gels", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam Double reactantPercent,
                                 @RequestParam String description,
                                 @RequestParam String picture)
    {
        ModelAndView post = new ModelAndView("admin-total");
        OpalescenseGel added = new OpalescenseGel();
        post.addObject("catalogue", base.getCatalogue());

        added.setTitle(title);
        added.setDescription(description);
        added.setVolume(volume);
        added.setReactantPercent(reactantPercent);
        added.setPrice(price);
        added.setPicture(picture);
        base.addProduct(added);
        return post;
    }

    @RequestMapping(value = "/admin/gels/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(@PathVariable int id,
                                 @RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam double reactantPercent,
                                 @RequestParam String description,
                                 @RequestParam String picture) {

        Product selectedProduct = base.getProductById(id);

        if (selectedProduct == null) {
            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "Sorry, the product with the ID does not exist");
            return view;
        }

        ModelAndView post = new ModelAndView("admin-total");
        OpalescenseGel updated = (OpalescenseGel) selectedProduct;// Casting
        updated.setTitle(title);
        updated.setDescription(description);
        updated.setVolume(volume);
        updated.setReactantPercent(reactantPercent);
        updated.setPrice(price);
        updated.setPicture(picture);
        base.updateProduct(updated);
        post.addObject("catalogue", base.getCatalogue());
        return post;
    }

}