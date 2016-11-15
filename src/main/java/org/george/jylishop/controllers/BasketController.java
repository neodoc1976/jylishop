package org.george.jylishop.controllers;

import org.george.jylishop.dao.BasketDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.domain.Basket;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by Yulya on 12.11.2016.
 */
@Controller
public class BasketController {

    @Autowired
    BasketDao basketDao;
    @Autowired
    ProductDao productDao;

    @RequestMapping("/basket")
    public ModelAndView showBasket() {
        ModelAndView model = new ModelAndView("basket");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> purchases = basketDao.getUserBasket(user.getUsername()).getPurchases();
        Map<Product, Integer> groupt_products = new HashMap<>();
        for (Product p : purchases) {
            Integer count = groupt_products.get(p);
            if (count != null) {
                count = count + 1;
                groupt_products.put(p, count);
            } else {
                groupt_products.put(p, 1);
            }
        }

        model.addObject("basket", groupt_products);
        return model;
    }

    @RequestMapping("/products/{id}/add_to_basket")
    public String addProductToBasket(@PathVariable int id) {
        Product selectedProduct = productDao.getProductById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Basket basket = basketDao.getUserBasket(user.getUsername());
        List<Product> purchases = basket.getPurchases();
        purchases.add(selectedProduct);
        basketDao.updateBasket(basket);

        return "redirect:/basket";
    }

    @RequestMapping("/products/{id}/remove_from_basket")
    public String removeProductFromBasket (@PathVariable int id){
        Product selectedProduct = productDao.getProductById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Basket basket = basketDao.getUserBasket(user.getUsername());
        List<Product> purchases = basket.getPurchases();
        purchases.remove(selectedProduct);
        basketDao.updateBasket(basket);

        return "redirect:/basket";
    }


}
