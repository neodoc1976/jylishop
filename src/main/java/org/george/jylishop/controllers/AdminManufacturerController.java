package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.db.PictureService;
import org.george.jylishop.domain.Manufacturer;
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
 * Created by Yulya on 18.07.2016.
 */
@Controller
public class AdminManufacturerController {
    @Autowired
    DataBase base;
    @Autowired
    PictureService pictureService;

    @RequestMapping("/admin/manufacturer")
    public ModelAndView contactMethod() {
        ModelAndView model = new ModelAndView("admin-manufacturer");
        model.addObject("manufacturers", base.getAllManufacturers());
        model.addObject("logos",pictureService.getAllLogo());
        return model;
    }
    @RequestMapping(value = "/admin/manufacturer", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam String logo)
    {
        ModelAndView post = new ModelAndView("admin-manufacturer");
        Manufacturer neoteric = new Manufacturer();
        post.addObject("manufacturers", base.getAllManufacturers());
        post.addObject("logos",pictureService.getAllLogo());

        neoteric.setName(name);
        neoteric.setDescription(description);
        neoteric.setLogo(logo);
        base.addManufacturer(neoteric);
        return post;
    }

    @RequestMapping(value = "/admin/manufacturer/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-manufacturer");
        view.addObject("manufacturers",base.getAllManufacturers());
        view.addObject("logos",pictureService.getAllLogo());
        return view;
    }

    @RequestMapping(value = "/admin/manufacturer/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-manufacturer");
        Manufacturer manufacturer = base.getManufacturerById(id);

        if (manufacturer == null) {
            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "Sorry, the manufacturer with the ID does not exist");
            return view;
        }

        base.deleteManufacturer(manufacturer);
        delete.addObject("manufacturers", base.getAllManufacturers());
        delete.addObject("logos",pictureService.getAllLogo());
        return delete;

    }


}
