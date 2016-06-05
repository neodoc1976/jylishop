package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.george.jylishop.utils.NameComparator;
import org.george.jylishop.utils.PriceCompartor;
import org.george.jylishop.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Yulya on 20.05.2016.
 */
@Controller
public class ProductController {
    @Autowired
    DataBase base;
    @Autowired
    ProductUtils utils;


    @RequestMapping({"/", "/total"})
    public ModelAndView totalList(@RequestParam(required = false) String sort) {
        ModelAndView total = new ModelAndView("total");
        total.addObject("catalogue", base.getCatalogue());
        if (sort != null && sort.equals("priceasc")) {
            PriceCompartor compartor = new PriceCompartor();
            List<Product> sorted = base.getCatalogue();
            sorted.sort(compartor);
            total.addObject("catalogue", sorted);
        }

        if (sort != null && sort.equals("name")) {
            NameComparator comparator = new NameComparator();
            List<Product> sorted = base.getCatalogue();
            sorted.sort(comparator);
            total.addObject("catalogue", sorted);
        }

        if (sort != null && sort.equals("pricedesc")) {
            PriceCompartor compartor = new PriceCompartor();
            List<Product> sorted = base.getCatalogue();
            sorted.sort(compartor);
            Collections.reverse(sorted);
            total.addObject("catalogue", sorted);
        }
        return total;
    }

    @RequestMapping({"/products/{id}"})
    public ModelAndView getProduct(@PathVariable int id) {
        Product selectedProduct = utils.getProductById(id);

        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("gel-product");
            view.addObject("opalescenseInfo", selectedProduct);
            return view;
        }

        if (selectedProduct instanceof Hemostatic) {
            ModelAndView view = new ModelAndView("hemo-product");
            view.addObject("hemoInfo", selectedProduct);
            return view;
        }

        ModelAndView view = new ModelAndView("error");
        view.addObject("message", " SORRY,PRODUCT IS NOT FOUND ");
        return view;
    }


}
