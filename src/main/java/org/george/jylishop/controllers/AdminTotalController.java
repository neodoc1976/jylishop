package org.george.jylishop.controllers;

import org.george.jylishop.dao.CommentDao;
import org.george.jylishop.dao.ManufacturerDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.domain.Comment;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
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



    @RequestMapping({"/admin"})
    public ModelAndView adminList(@RequestParam(required = false) String sort) {
        ModelAndView admin = new ModelAndView("admin-total");
        admin.addObject("catalogue", productDao.getCatalogue());
        if (sort != null && sort.equals("orderbyid")){
            List<Product> sorted = productDao.getCatalogueOrderById();
            admin.addObject("catalogue",sorted);
        }
        return admin;
    }

    @RequestMapping(value = "/admin/product/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-total");
        Product selectedProduct = productDao.getProductById(id);

        if (selectedProduct == null) {
            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "Sorry, the product with the ID does not exist");
            return view;
        }

        productDao.deleteProduct(selectedProduct);

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/product/{id}/update", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable int id) {
        Product selectedProduct=null;
        selectedProduct = productDao.getProductById(id);

        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("admin-update-gel");
            view.addObject("recall", selectedProduct);
            view.addObject("manufacturers",manufacturerDao.getAllManufacturers());
            view.addObject("pictures",pictureService.getAllPictures());
            return view;
        }

        if (selectedProduct instanceof Hemostatic) {
            ModelAndView view = new ModelAndView("admin-update-hemo");
            view.addObject("recall", selectedProduct);
            view.addObject("manufacturers",manufacturerDao.getAllManufacturers());
            view.addObject("pictures",pictureService.getAllPictures());

            return view;
        }

        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "Sorry, the product with the ID does not exist");
        return view;
    }

   @RequestMapping("/admin/comments")
    public ModelAndView seeAllComments(){
       ModelAndView view = new ModelAndView("admin-comments");
       List<Comment> comments = commentDao.getAllComment();
       view.addObject("comments",comments);
       return view;
   }

}