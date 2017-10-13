package org.george.jylishop.controllers;

import org.george.jylishop.dao.ManufacturerDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.services.PictureService;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.OpalescenseGel;
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
 * Created by Yulya on 03.05.2016.
 */
@Controller
public class AdminGelController {
    @Autowired
    PictureService pictureService;
    @Autowired
    TextFileService textFileService;
    @Autowired
    ManufacturerDao manufacturerDao;
    @Autowired
    ProductDao productDao;



    @RequestMapping(value = "/admin/gels/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-gel");
        view.addObject("manufacturers",manufacturerDao.getAllManufacturers());
        view.addObject("pictures",pictureService.getAllPictures());
        return view;
    }

    @RequestMapping(value = "/admin/gels", method = RequestMethod.POST)
    public String postForm(@RequestParam String title,
                           @RequestParam Double volume,
                           @RequestParam Double price,
                           @RequestParam Double reactantPercent,
                           @RequestParam (required = false)String description,
                           @RequestParam (required = false) String picture,
                           @RequestParam int manufacturerId,
                           @RequestParam int quantity,
                           @RequestParam ("photo") MultipartFile photo,
                           @RequestParam ("description_text") MultipartFile description_text) throws IOException {

//        ModelAndView post = new ModelAndView("admin-total");
        OpalescenseGel added = new OpalescenseGel();

        if (photo.isEmpty()){
            added.setPicture(picture);
        }
        else{
            added.setPicture(photo.getOriginalFilename());
            pictureService.saveProductPhoto(photo.getInputStream(),photo.getOriginalFilename());
        }
        if (description_text.isEmpty()){
            added.setDescription(description);
        }
        else {
            added.setDescription(textFileService.readDescription(description_text.getInputStream()));


        }

        added.setTitle(title);
        added.setVolume(volume);
        added.setReactantPercent(reactantPercent);
        added.setPrice(price);
        added.setManufacturer(manufacturerDao.getManufacturerById(manufacturerId));
        added.setQuantity(quantity);
        productDao.addProduct(added);
//        post.addObject("catalogue", base.getCatalogue());
        return  "redirect:/admin";
    }
//Updating
    @RequestMapping(value = "/admin/gels/{id}/update", method = RequestMethod.POST)
    public ModelAndView editForm(@PathVariable int id,
                                 @RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam double reactantPercent,
                                 @RequestParam String description,
                                 @RequestParam String picture,
                                 @RequestParam int quantity,
                                 @RequestParam int manufacturerId) {

        Product selectedProduct = productDao.getProductById(id);
        OpalescenseGel updated = (OpalescenseGel) selectedProduct;// Casting
        updated.setTitle(title);
        updated.setDescription(description);
        updated.setVolume(volume);
        updated.setReactantPercent(reactantPercent);
        updated.setPrice(price);
        updated.setPicture(picture);
        updated.setQuantity(quantity);
        Manufacturer manufacturer = manufacturerDao.getManufacturerById(manufacturerId);
        updated.setManufacturer(manufacturer);
        productDao.updateProduct(updated);
//        post.addObject("catalogue", base.getCatalogue());
       return new ModelAndView ("redirect:/admin");
    }
    //Getting of products

    @RequestMapping(value = "/admin/products/{id}/getting", method = RequestMethod.POST)
    public ModelAndView gettingGel(@PathVariable int id,
                                 @RequestParam Double price,
                                 @RequestParam int batchQuantity) {

        Product selectedProduct = productDao.getProductById(id);
        int storageQuantity = selectedProduct.getQuantity();
        int newQuantity = storageQuantity + batchQuantity;
        selectedProduct.setQuantity(newQuantity );
        selectedProduct.setPrice(price);
        productDao.updateProduct(selectedProduct);
//        post.addObject("catalogue", base.getCatalogue());
        return new ModelAndView ("redirect:/admin");
    }

}