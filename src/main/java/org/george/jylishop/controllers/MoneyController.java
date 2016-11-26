package org.george.jylishop.controllers;

import org.apache.velocity.tools.generic.NumberTool;
import org.george.jylishop.dao.MoneyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yulya on 26.11.2016.
 */
@Controller
public class MoneyController {

    @Autowired
    MoneyDao moneyDao;

    @RequestMapping ("/admin/money")
        public ModelAndView getStatistic (){
        ModelAndView model = new ModelAndView("admin-money");
        model.addObject("statistic",moneyDao.getPurchasesHistory());
        model.addObject("tool", new NumberTool());
        return model;

    }

    @RequestMapping ("/admin/money/{username}/user_statistic")
    public ModelAndView userPurchasesStatistic (@PathVariable String username){
        ModelAndView model = new ModelAndView("admin-money-user");
        model.addObject("user_history",moneyDao.getUserPurchasesHistory(username));
        model.addObject("user_name",username);
        model.addObject("tool", new NumberTool());
        return model;

    }

}
