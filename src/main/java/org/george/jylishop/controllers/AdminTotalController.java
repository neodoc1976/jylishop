package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
