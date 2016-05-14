package org.george.jylishop.controllers;

import org.george.jylishop.domain.ContactInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yulya on 02.05.2016.
 */
@Controller
public class ContactController {
    @RequestMapping("/contact")
    public ModelAndView hellomethod() {
        ModelAndView model = new ModelAndView("contact");
        ContactInfo ci = new ContactInfo();
        ci.setName("George");
        ci.setEmail("neodoc3@gmail.com");
        ci.setAddress("Kiyv,Kasyana str,2,office 97");
        ci.setTelephone("066-381-86-14");

        model.addObject("contactInfo", ci);

        return model;
    }


}
