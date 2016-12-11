package org.george.jylishop.controllers;

import org.apache.velocity.tools.generic.NumberTool;
import org.george.jylishop.dao.PurchasesTransactionDao;
import org.george.jylishop.domain.Product;
import org.george.jylishop.domain.PurchaseTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Yulya on 26.11.2016.
 */
@Controller
public class PurchasesTransactionController {

    @Autowired
    PurchasesTransactionDao purchasesTransactionDao;

    @RequestMapping("/admin/money")
    public ModelAndView getStatistic() {
        ModelAndView model = new ModelAndView("admin-money");
        model.addObject("statistic", purchasesTransactionDao.getPurchasesHistory());
        model.addObject("tool", new NumberTool());
        return model;

    }

    @RequestMapping("/admin/money/{username}/user_statistic")
    public ModelAndView userPurchasesStatistic(@PathVariable String username) {
        ModelAndView model = new ModelAndView("admin-money-user");
        model.addObject("user_history", purchasesTransactionDao.getUserPurchasesHistory(username));
        model.addObject("user_name", username);
        model.addObject("tool", new NumberTool());
        return model;

    }

    @RequestMapping("/admin/money/{id}/user_statistic/invoices")
    public ModelAndView getInvoices(@PathVariable int id) {
        ModelAndView model = new ModelAndView("invoices");
        List<Product> purcases = purchasesTransactionDao.getUserInvoiceById(id).getPurchases();
        PurchaseTransaction transaction = new PurchaseTransaction();
        Map<Product,Integer> map = transaction.getGroupedProduct(purcases);


        model.addObject("map",map);
        model.addObject("tool",new NumberTool());
        model.addObject("user_history", purchasesTransactionDao.getUserInvoiceById(id));
        return model;

    }
}
