package org.george.jylishop.controllers;

import org.george.jylishop.db.DataBase;
import org.george.jylishop.db.UserBase;
import org.george.jylishop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Yulya on 23.10.2016.
 */
@Controller
public class RegistrationController {
    @Autowired
    UserBase userBase;

    @RequestMapping (value = "/registration", method = RequestMethod.GET)

    public ModelAndView getForm (){
        ModelAndView view = new ModelAndView("registration");
        return view;
    }

    @RequestMapping (value="/registration", method= RequestMethod.POST)

    public String fillForm (@RequestParam String username,
                            @RequestParam String password) {

        User user = new User();
        user.setEnabled(true);
        user.setUsername(username);
        user.setPassword(password);
        List <String> list=user.getRoles();
        list.add("ROLE_USER");
        userBase.storeNewUser(user);
        return "redirect:/spring_security_login";

    }







}
