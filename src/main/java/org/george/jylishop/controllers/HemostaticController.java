//package org.george.jylishop.controllers;
//
//import org.george.jylishop.db.DataBase;
//import org.george.jylishop.domain.Hemostatic;
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
// * Created by Yulya on 16.05.2016.
// */
//@Controller
//public class HemostaticController {
//    @Autowired
//    DataBase base;
//
//    @RequestMapping("/hemos/{id}")
//    public ModelAndView detailInfoMethod(@PathVariable int id) {
//        ModelAndView part = new ModelAndView("hemo-product");
//        ArrayList<Hemostatic> hemolist = base.getHemolist();
//        part.addObject("hemoInfo",hemolist.get(id));
//        return part;
//    }
//
//
//    @RequestMapping("/hemos")
//    public ModelAndView hemoListMethod(){
//        ModelAndView entire = new ModelAndView("hemo-list");
//        entire.addObject("hemolist",base.getHemolist());
//        return entire;
//    }
//
//
//
//
//
//
//
//
//}
