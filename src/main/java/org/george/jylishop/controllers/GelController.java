//package org.george.jylishop.controllers;
//
//import org.george.jylishop.db.DataBase;
//import org.george.jylishop.domain.OpalescenseGel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//
///**
// * Created by Yulya on 02.05.2016.
// */
//@Controller
//public class GelController {
//     @Autowired
//     DataBase base;
//
//    @RequestMapping("/gels/{id}")
//    public ModelAndView detailInfoMethod(@PathVariable int id) {
//        ModelAndView model = new ModelAndView("gel-product");
//        ArrayList<OpalescenseGel> catalogue = base.getCatalogue();
//        model.addObject("opalescenseInfo",catalogue.get(id));
//        return model;
//    }
//
//    @RequestMapping("/gels")
//    public ModelAndView gelList() {
//        ModelAndView list = new ModelAndView("gel-list");
//        list.addObject("catalogue", base.getCatalogue());
//        return list;
//
//    }
//
//
//}
//
