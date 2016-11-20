package org.george.jylishop.controllers;

import org.george.jylishop.dao.ManufacturerDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.services.PictureService;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.Product;
import org.george.jylishop.services.TextFileService;
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
    PictureService pictureService;
    @Autowired
    TextFileService textFileService;
    @Autowired
    ManufacturerDao manufacturerDao;
    @Autowired
    ProductDao productDao;

    @RequestMapping("/admin/manufacturer")
    public ModelAndView allManufacturer() {
        ModelAndView model = new ModelAndView("admin-manufacturer");
        model.addObject("manufacturers", manufacturerDao.getAllManufacturers());
        model.addObject("logos", pictureService.getAllLogo());

        return model;
    }


    @RequestMapping(value = "/admin/manufacturer", method = RequestMethod.POST)
    public String postForm(@RequestParam String name,
                           @RequestParam(required = false) String description,
                           @RequestParam("description_file") MultipartFile description_file,
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

        if (description_file.isEmpty()) {
            neoteric.setDescription(description);

        } else {
            neoteric.setDescription(textFileService.readDescription(description_file.getInputStream()));
        }

        neoteric.setName(name);
        manufacturerDao.addManufacturer(neoteric);


        return "redirect:/admin/manufacturer";
    }

    @RequestMapping(value = "/admin/manufacturer/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-manufacturer");
        view.addObject("manufacturers", manufacturerDao.getAllManufacturers());
        view.addObject("logos", pictureService.getAllLogo());
        return view;
    }

    @RequestMapping(value = "/admin/manufacturer/{id}/update", method = RequestMethod.POST)
    public String updateForm(@PathVariable int id,
                             @RequestParam String name,
                             @RequestParam String logo,
                             @RequestParam String description) {
        ModelAndView view = new ModelAndView("admin-manufacturer");
        Manufacturer updated = manufacturerDao.getManufacturerById(id);
        updated.setName(name);
        updated.setLogo(logo);
        updated.setDescription(description);
        manufacturerDao.updateManufacturer(updated);
        return "redirect:/admin/manufacturer";



    }

    @RequestMapping(value = "/admin/manufacturer/{id}/update", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable int id) {
        Manufacturer selectedManufacturer = new Manufacturer();
        selectedManufacturer = manufacturerDao.getManufacturerById(id);
        ModelAndView view = new ModelAndView("admin-update-manufacturer");
        view.addObject("recall", selectedManufacturer);
        view.addObject("manufacturers", manufacturerDao.getAllManufacturers());
        view.addObject("logos", pictureService.getAllLogo());
        return view;
    }


    @RequestMapping(value = "/admin/manufacturer/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-manufacturer");
        Manufacturer manufacturer = manufacturerDao.getManufacturerById(id);


        List<Product> list = productDao.getProductListByManufacturer(id);
        if (list.size() != 0) {

            ModelAndView view = new ModelAndView("manufacturer-delete-menu");
            view.addObject("message", "You can not remove the manufacturer, because the database are products of this manufacturer");
            view.addObject("products", list);
            view.addObject("man", manufacturerDao.getAllManufacturers());
            return view;
        } else {

            manufacturerDao.deleteManufacturer(manufacturer);
            delete.addObject("manufacturers", manufacturerDao.getAllManufacturers());
            delete.addObject("logos", pictureService.getAllLogo());
            return delete;
        }

    }

    @RequestMapping(value = "/admin/manufacturer/{id}/force_delete", method = RequestMethod.GET)
    public ModelAndView deleteListForm(@PathVariable Integer id) {
        ModelAndView deleteList = new ModelAndView("manufacturer-delete-menu");
        productDao.deleteProductListByManufacturer(id);
        manufacturerDao.deleteManufacturer(manufacturerDao.getManufacturerById(id));
        deleteList.addObject("id", id);
        deleteList.addObject("message", "All products of the selected manufacturer and the manufacturer has been removed");
        return deleteList;

    }

    @RequestMapping(value = "admin/manufacturer/{oldId}/replace-with/", method = RequestMethod.POST)
    public ModelAndView changeForm(@PathVariable int oldId,
                                   @RequestParam int newId) {
        ModelAndView change = new ModelAndView("manufacturer-delete-menu");
        productDao.changeManufacturerForProducts(newId, oldId);
        manufacturerDao.deleteManufacturer(manufacturerDao.getManufacturerById(oldId));
        change.addObject("message", "Manufacturer was changed");
        change.addObject("oldId", oldId);
        change.addObject("newId", newId);
        return change;

    }


}




