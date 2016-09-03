package org.george.jylishop.controllers;


import org.george.jylishop.db.DataBase;
import org.george.jylishop.services.PictureService;
import org.george.jylishop.domain.Hemostatic;
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

/**
 * Created by Yulya on 21.05.2016.
 */
@Controller
public class AdminHemoController {
    @Autowired
    DataBase base;
    @Autowired
    PictureService pictureService;
    @Autowired
    TextFileService textFileService;


    @RequestMapping(value = "/admin/hemos/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-hemo");
        view.addObject("manufacturers",base.getAllManufacturers());
        view.addObject("pictures",pictureService.getAllPictures());
        return view;
    }

    @RequestMapping(value = "/admin/hemos", method = RequestMethod.POST)
    public ModelAndView postForm(@RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam String substance,
                                 @RequestParam (required = false)String description,
                                 @RequestParam (required = false)String picture,
                                 @RequestParam int manufacturerId,
                                 @RequestParam ("photo")MultipartFile photo,
                                 @RequestParam ("description_text") MultipartFile description_text)
                                throws IOException {

        ModelAndView post = new ModelAndView("admin-total");
        Hemostatic newcomer = new Hemostatic();

        if (photo.isEmpty()) {
            newcomer.setPicture(picture);
        }
        else{
            newcomer.setPicture(photo.getOriginalFilename());
            pictureService.saveProductPhoto(photo.getInputStream(),photo.getOriginalFilename());
        }
        if (description_text.isEmpty()) {
            newcomer.setDescription(description);

        }

        else {

            newcomer.setDescription(textFileService.readDescription(description_text.getInputStream()));
        }



        newcomer.setTitle(title);
        newcomer.setVolume(volume);
        newcomer.setHemostaticSubstance(substance);
        newcomer.setPrice(price);
        newcomer.setManufacturer(base.getManufacturerById(manufacturerId));
        base.addProduct(newcomer);
        post.addObject("catalogue", base.getCatalogue());
        return post;

    }


    @RequestMapping(value = "/admin/hemos/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(@PathVariable int id,
                                 @RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam String hemostaticSubstance,
                                 @RequestParam String description,
                                 @RequestParam String picture,
                                 @RequestParam int manufacturerId) {

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
        Manufacturer manufacturer=base.getManufacturerById(manufacturerId);
        updated.setManufacturer(manufacturer);
        base.updateProduct(updated);
        post.addObject("catalogue", base.getCatalogue());
        return post;
    }

}



