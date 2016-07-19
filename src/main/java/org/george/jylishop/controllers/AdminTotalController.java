package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.db.PictureService;
import org.george.jylishop.domain.Hemostatic;
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
 * Created by Yulya on 20.05.2016.
 */
@Controller
public class AdminTotalController {
    @Autowired
    DataBase base;
    @Autowired
    PictureService pictureService;



    @RequestMapping({"/admin","/aaa","/фвьшт"})
    public ModelAndView adminList(@RequestParam(required = false) String sort) {
        ModelAndView admin = new ModelAndView("admin-total");
        admin.addObject("catalogue", base.getCatalogue());
        if (sort != null && sort.equals("orderbyid")){
            List<Product> sorted = base.getCatalogueOrderById();
            admin.addObject("catalogue",sorted);
        }
        return admin;
    }

    @RequestMapping(value = "/admin/product/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-total");
        Product selectedProduct = base.getProductById(id);

        if (selectedProduct == null) {
            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "Sorry, the product with the ID does not exist");
            return view;
        }

//        base.getCatalogue().remove(selectedProduct);
        base.deleteProduct(selectedProduct);
        delete.addObject("catalogue", base.getCatalogue());
        return delete;

    }

    @RequestMapping(value = "/admin/product/{id}/update", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable int id) {
        Product selectedProduct=null;
        selectedProduct = base.getProductById(id);

        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("admin-update-gel");
            view.addObject("recall", selectedProduct);
            view.addObject("manufacturers",base.getAllManufacturers());
            view.addObject("pictures",pictureService.getAllPictures());
            return view;
        }

        if (selectedProduct instanceof Hemostatic) {
            ModelAndView view = new ModelAndView("admin-update-hemo");
            view.addObject("recall", selectedProduct);
            view.addObject("manufacturers",base.getAllManufacturers());
            view.addObject("pictures",pictureService.getAllPictures());

            return view;
        }

        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "Sorry, the product with the ID does not exist");
        return view;
    }

}