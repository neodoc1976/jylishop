package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.OpalescenseGel;
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
 * Created by Yulya on 03.05.2016.
 */
@Controller
public class AdminProductController {
    @Autowired
    DataBase base;

    @RequestMapping(value = "admin/products/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-product");
        return view;
    }

    @RequestMapping(value = "admin/products", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String title, @RequestParam Double volume,
                                 @RequestParam Double price, @RequestParam Integer reactantPercent, @RequestParam String description) {
        ModelAndView post = new ModelAndView("product");
        // post.addObject("title", title);
        // post.addObject("reactantPercent", reactantPercent);
        // post.addObject("volume", volume);
        // post.addObject("price", price);
        //  post.addObject("description", description);
        OpalescenseGel added = new OpalescenseGel();
        post.addObject("opalescenseInfo", added);
        added.setTitle(title);
        added.setDescription(description);
        added.setVolume(volume);
        added.setReactantPercent(reactantPercent);
        added.setPrice(price);
        List<OpalescenseGel> list = base.getCatalogue();
        list.add(added);
        return post;

    }

    @RequestMapping(value = "admin/products/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("list");
        List<OpalescenseGel> list = base.getCatalogue();
        list.remove(id);
        delete.addObject("catalogue", base.getCatalogue());
        return delete;
    }

    @RequestMapping("/admin")
    public ModelAndView adminForm() {
        ModelAndView admin = new ModelAndView("admin-start");
        return admin;

    }

    @RequestMapping("/admin/list")
    public ModelAndView productList() {
        ModelAndView list = new ModelAndView("admin-list");
        list.addObject("catalogue", base.getCatalogue());
        return list;
    }

    @RequestMapping(value = "/admin/products/{id}/update", method = RequestMethod.GET)
    public ModelAndView getFilledForm(@PathVariable int id) {
        ModelAndView update = new ModelAndView("admin-update-product");
        ArrayList<OpalescenseGel> recall = base.getCatalogue();
        update.addObject("recall", recall.get(id));
        return update;


    }

    @RequestMapping(value = "/admin/products/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(@PathVariable int id,
                                 @RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam Double reactantPercent,
                                 @RequestParam String description) {
        ModelAndView post = new ModelAndView("admin-list");
        ArrayList<OpalescenseGel> catalogue = base.getCatalogue();
        OpalescenseGel added = catalogue.get(id);
        added.setTitle(title);
        added.setDescription(description);
        added.setVolume(volume);
        added.setReactantPercent(reactantPercent);
        added.setPrice(price);
        post.addObject("catalogue", base.getCatalogue());
        return post;


    }


}
