package org.george.jylishop.controllers;

import org.george.jylishop.dao.UserDao;
import org.george.jylishop.domain.User;
import org.george.jylishop.domain.UserRole;
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
    UserDao userDao;


    @RequestMapping (value = "/registration", method = RequestMethod.GET)

    public ModelAndView getForm (){
        ModelAndView view = new ModelAndView("registration");
        return view;
    }

    @RequestMapping (value="/registration", method= RequestMethod.POST)

    public String fillForm (@RequestParam String username,
                            @RequestParam String password) {

        User user = new User();
        user.setEnabled(1);//Because type in base "boolean" , but in User.class this type is integer
        user.setUsername(username);
        user.setPassword(password);
        List <UserRole> list=user.getRoles();
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        list.add(userRole);
        userDao.storeNewUser(user);
        return "redirect:/spring_security_login";

    }



}
