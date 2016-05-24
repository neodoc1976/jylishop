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
        ci.setLocation("<iframe src=\"https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d2544.3714603077833!2d30.4572485!3d50.3782736!3m2!1i1024!2i768!4f13.1!5e0!3m2!1suk!2sua!4v1464116236726\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>");

        model.addObject("contactInfo", ci);

        return model;
    }


}
