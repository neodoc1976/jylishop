package org.george.jylishop.controllers;

import org.apache.velocity.tools.generic.NumberTool;
import org.george.jylishop.dao.BasketDao;
import org.george.jylishop.dao.MoneyDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.dao.UserDao;
import org.george.jylishop.domain.*;
import org.george.jylishop.domain.utils.ProductNameComparator;
import org.george.jylishop.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
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
    @Autowired
    UserDao userDao;
    @Autowired
    MoneyDao moneyDao;

    @RequestMapping("/basket")
    public ModelAndView showBasket() {
        ModelAndView model = new ModelAndView("basket");
        List<Product> purchases = basketDao.getUserBasket(SecurityUtils.getCurrentUsername()).getPurchases();
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
        Set<Product> keySet = groupt_products.keySet();
        List<Product> list = new ArrayList<>(keySet);

        list.sort(new ProductNameComparator());

        model.addObject("list", list);
        model.addObject("basket", groupt_products);
        model.addObject("user_name", SecurityUtils.getCurrentUsername());
        model.addObject("tool", new NumberTool());
        return model;
    }


    @RequestMapping("/products/{id}/add_to_basket")
    public ModelAndView addProductToBasket(@PathVariable int id) {
        if (SecurityUtils.getCurrentUsername() == null) {

            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "You Are Not Log In Or Registred.");
            return view;


        } else {
            Product selectedProduct = productDao.getProductById(id);
            Basket basket = basketDao.getUserBasket(SecurityUtils.getCurrentUsername());
            List<Product> purchases = basket.getPurchases();
            Basket basket1 = new Basket();
            int count = basket1.productInBasketCount(purchases, id);
            if (selectedProduct.getQuantity() <= count) {

                return new ModelAndView("redirect:/basket");

            } else {
                purchases.add(selectedProduct);
                basketDao.updateBasket(basket);
            }

            return new ModelAndView("redirect:/basket");
        }
    }

    @RequestMapping("/products/{id}/remove_from_basket")
    public String removeProductFromBasket(@PathVariable int id) {
        Basket basket = basketDao.getUserBasket(SecurityUtils.getCurrentUsername());
        List<Product> purchases = basket.getPurchases();

        for (Product p : purchases) {
            int product_id = p.getId();
            if (product_id == id) {
                purchases.remove(p);
                break;
            }
        }
        basketDao.updateBasket(basket);

        return "redirect:/basket";


    }

    @RequestMapping("/products/basket/buy")
    public ModelAndView buyMethod() {

        Basket basket = basketDao.getUserBasket(SecurityUtils.getCurrentUsername());
        List<Product> purchases = basket.getPurchases();
        double totalCostOfBasket = 0;
        for (Product p : purchases) {
            totalCostOfBasket = totalCostOfBasket + p.getPrice();
        }

        double userMoney = userDao.getUserInfo(SecurityUtils.getCurrentUsername()).getMoney();
        if (totalCostOfBasket > userMoney) {

            ModelAndView view = new ModelAndView("error");
            view.addObject("message", "You Do Not Have Enough Money In Your Account");
            return view;

        } else {

            Product changeableProduct;
            int quantity;
            for (Product p : purchases) {
                changeableProduct = productDao.getProductById(p.getId());
                quantity = changeableProduct.getQuantity() - 1;
                changeableProduct.setQuantity(quantity);
                productDao.updateProduct(changeableProduct);
            }
            User user = userDao.getUserInfo(SecurityUtils.getCurrentUsername());
            user.setMoney(userMoney - totalCostOfBasket);
            userDao.updateCurrentUserInfo(user);
            purchases.clear();
            basket.setPurchases(purchases);
            basketDao.updateBasket(basket);
            Money money=new Money();
            money.setEarnings(totalCostOfBasket);
            money.setUser_name(user.getUsername());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            money.setData(dateFormat.format(date));
            moneyDao.addNewEarnings(money);


        }


        return new ModelAndView("redirect:/total");
    }


}
