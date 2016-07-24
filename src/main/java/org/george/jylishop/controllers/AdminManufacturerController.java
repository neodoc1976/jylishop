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

import java.util.List;

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
        model.addObject("logos", pictureService.getAllLogo());
        return model;
    }

    @RequestMapping(value = "/admin/manufacturer", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam String logo) {
        ModelAndView post = new ModelAndView("admin-manufacturer");
        Manufacturer neoteric = new Manufacturer();
        post.addObject("manufacturers", base.getAllManufacturers());
        post.addObject("logos", pictureService.getAllLogo());

        neoteric.setName(name);
        neoteric.setDescription(description);
        neoteric.setLogo(logo);
        base.addManufacturer(neoteric);
        return post;
    }

    @RequestMapping(value = "/admin/manufacturer/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-manufacturer");
        view.addObject("manufacturers", base.getAllManufacturers());
        view.addObject("logos", pictureService.getAllLogo());
        return view;
    }

    @RequestMapping(value = "/admin/manufacturer/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-manufacturer");
        Manufacturer manufacturer = base.getManufacturerById(id);


        List<Product> list = base.getProductListByManufacturer(id);
        if (list.size() != 0) {

            ModelAndView view = new ModelAndView("manufacturer-delete-menu");
            view.addObject("message", "You can not remove the manufacturer, because the database are products of this manufacturer");
            view.addObject("products", list);
            view.addObject("man",base.getAllManufacturers());
            return view;
        } else {

            base.deleteManufacturer(manufacturer);
            delete.addObject("manufacturers", base.getAllManufacturers());
            delete.addObject("logos", pictureService.getAllLogo());
            return delete;
        }

    }

    @RequestMapping(value = "/admin/manufacturer/{id}/force_delete", method = RequestMethod.GET)
    public ModelAndView deleteListForm(@PathVariable Integer id) {
        ModelAndView deleteList = new ModelAndView("manufacturer-delete-menu");
        base.deleteProductListByManufacturer(id);
        base.deleteManufacturer(base.getManufacturerById(id));
        deleteList.addObject("id",id);
        deleteList.addObject("message", "All products of the selected manufacturer and the manufacturer has been removed");
        return deleteList;

    }
    @RequestMapping(value = "admin/manufacturer/{oldId}/replace-with/",method =RequestMethod.POST)
    public ModelAndView changeForm(@PathVariable int oldId,
                                   @RequestParam int newId) {
        ModelAndView change = new ModelAndView("manufacturer-delete-menu");
        base.changeManufacturerForProducts(newId, oldId);
        base.deleteManufacturer(base.getManufacturerById(oldId));
        change.addObject("message", "Manufacturer was changed");
        change.addObject("oldId", oldId);
        change.addObject("newId", newId);
        return change;

    }


}




