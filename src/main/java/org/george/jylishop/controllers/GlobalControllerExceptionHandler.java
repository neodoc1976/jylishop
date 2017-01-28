package org.george.jylishop.controllers;

import org.george.jylishop.exceptoins.ContactIsNotFoundException;
import org.george.jylishop.exceptoins.ManufacturerIsNotFoundException;
import org.george.jylishop.exceptoins.ProductIsNotFoundException;
import org.george.jylishop.exceptoins.PurchaseIsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yulya on 14.01.2017.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ProductIsNotFoundException.class)
    public ModelAndView handleNoSuchElementException(ProductIsNotFoundException e) {
        ModelAndView error = new ModelAndView("error");
        error.addObject("message", "Product Where Id " + e.getProductId() + " Is Not Found");
        return error;
    }

    @ExceptionHandler(ContactIsNotFoundException.class)
    public ModelAndView handleNoSuchElementexception() {
        ModelAndView error = new ModelAndView("error");
        error.addObject("message", "Contact is Not Found");
        return error;
    }

    @ExceptionHandler(ManufacturerIsNotFoundException.class)
    public ModelAndView handleNoSuchElementExceptions(ManufacturerIsNotFoundException e) {
        ModelAndView error = new ModelAndView("error");
        error.addObject("message", "Manufacturer With Id " + e.getManufacturerId() + " Does Not Exists");
        return error;
    }

    @ExceptionHandler(PurchaseIsNotFoundException.class)
    public ModelAndView handleNoSuchElementExceptions(PurchaseIsNotFoundException e) {
        ModelAndView error = new ModelAndView("error");
        error.addObject("message", "Purchase Transaction With Id " + e.getPurchaseTransactionId() + " Does Not Exists");
        return error;
    }


}
