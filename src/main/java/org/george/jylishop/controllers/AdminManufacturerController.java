package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.services.PictureService;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
                                 @RequestParam(required = false) String logo,
                                 @RequestParam("photo") MultipartFile photo) throws IOException {
        ModelAndView post = new ModelAndView("admin-manufacturer");
        Manufacturer neoteric = new Manufacturer();
        if (photo.isEmpty()) {
            neoteric.setLogo(logo);
        } else {
            neoteric.setLogo(photo.getOriginalFilename());
            pictureService.saveLogoPhoto(photo.getInputStream(), photo.getOriginalFilename());
        }

        neoteric.setName(name);
        neoteric.setDescription(description);
        base.addManufacturer(neoteric);

        post.addObject("manufacturers", base.getAllManufacturers());
        post.addObject("logos", pictureService.getAllLogo());
        return post;
    }

    @RequestMapping(value = "/admin/manufacturer/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-manufacturer");
        view.addObject("manufacturers", base.getAllManufacturers());
        view.addObject("logos", pictureService.getAllLogo());
        return view;
    }

    @RequestMapping(value = "/admin/manufacturer/{id}/update", method = RequestMethod.POST)
    public ModelAndView updateForm(@PathVariable int id,
                                   @RequestParam String name,
                                   @RequestParam String logo,
                                   @RequestParam String description) {
        ModelAndView view = new ModelAndView("admin-manufacturer");
        Manufacturer updated = base.getManufacturerById(id);
        updated.setName(name);
        updated.setLogo(logo);
        updated.setDescription(description);
        base.updateManufacturer(updated);
        view.addObject("manufacturers", base.getAllManufacturers());
        view.addObject("logos", pictureService.getAllLogo());
        return view;
    }

    @RequestMapping(value = "/admin/manufacturer/{id}/update", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable int id) {
        Manufacturer selectedManufacturer = new Manufacturer();
        selectedManufacturer = base.getManufacturerById(id);
        ModelAndView view = new ModelAndView("admin-update-manufacturer");
        view.addObject("recall", selectedManufacturer);
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
            view.addObject("man", base.getAllManufacturers());
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
        deleteList.addObject("id", id);
        deleteList.addObject("message", "All products of the selected manufacturer and the manufacturer has been removed");
        return deleteList;

    }

    @RequestMapping(value = "admin/manufacturer/{oldId}/replace-with/", method = RequestMethod.POST)
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




