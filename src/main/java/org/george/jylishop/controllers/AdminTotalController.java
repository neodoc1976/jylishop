package org.george.jylishop.controllers;

import org.george.jylishop.dao.CommentDao;
import org.george.jylishop.dao.ManufacturerDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.domain.*;
import org.george.jylishop.services.PictureService;
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
 * Created by Yulya on 20.05.2016.
 */
@Controller
public class AdminTotalController {
    @Autowired
    PictureService pictureService;
    @Autowired
    ManufacturerDao manufacturerDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    CommentDao commentDao;


    @RequestMapping({"/admin", "/фвьшт"})
    public ModelAndView adminList(@RequestParam(required = false) String sort) {
        ModelAndView admin = new ModelAndView("admin-total");
        admin.addObject("catalogue", productDao.getCatalogue());
        if (sort != null && sort.equals("orderbyid")) {
            List<Product> sorted = productDao.getCatalogueOrderById();
            admin.addObject("catalogue", sorted);
        }
        return admin;
    }

    @RequestMapping(value = "/admin/product/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        Product selectedProduct = productDao.getProductById(id);
        productDao.deleteProduct(selectedProduct);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/product/{id}/update", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable int id) {
        Product selectedProduct;
        selectedProduct = productDao.getProductById(id);

        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("admin-update-gel");
            view.addObject("recall", selectedProduct);
            view.addObject("manufacturers", manufacturerDao.getAllManufacturers());
            view.addObject("pictures", pictureService.getAllPictures());
            return view;
        }

        if (selectedProduct instanceof Hemostatic) {
            ModelAndView view = new ModelAndView("admin-update-hemo");
            view.addObject("recall", selectedProduct);
            view.addObject("manufacturers", manufacturerDao.getAllManufacturers());
            view.addObject("pictures", pictureService.getAllPictures());

            return view;
        }

        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "Sorry, the product with the ID does not exist");
        return view;
    }



    @RequestMapping(value = "/errors/404.html")
    public ModelAndView handle404() {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", "Error 404 Happens. Wrong adress.");
        return modelAndView;
    }

}