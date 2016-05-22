package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Yulya on 20.05.2016.
 */
@Controller
public class AdminTotalController {
    @Autowired
    DataBase base;

    @RequestMapping("/admin")
    public ModelAndView adminList() {
        ModelAndView admin = new ModelAndView("admin-total");
        admin.addObject("catalogue", base.getCatalogue());
        return admin;
    }

    @RequestMapping(value = "/admin/product/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(@PathVariable int id) {
        ModelAndView delete = new ModelAndView("admin-total");
        for (Product p : base.getCatalogue()) {
            if (p.getId() == id) {
                base.getCatalogue().remove(p);
                delete.addObject("catalogue", base.getCatalogue());
                return delete;

            }

        }

        ModelAndView view = new ModelAndView ("error");
        view.addObject("message", "Sorry, the product with the ID does not exist");
        return view;

    }
    @RequestMapping(value = "/admin/product/{id}/update", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable int id) {
        Product selectedProduct = null;
        for (Product p : base.getCatalogue()) {
            if (p.getId() == id) {
                selectedProduct = p;
            }
        }
        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("admin-update-gel");
            view.addObject("recall", selectedProduct);
            return view;
        }
        if (selectedProduct instanceof Hemostatic){
            ModelAndView view = new ModelAndView("admin-update-hemo");
            view.addObject("recall",selectedProduct);
            return view;

        }


        ModelAndView view = new ModelAndView ("error");
        view.addObject("message", "Sorry, the product with the ID does not exist");
        return view;
    }


}