package org.george.jylishop.test.controllers;



import org.george.jylishop.controllers.AdminTotalController;
import org.george.jylishop.db.DataBase;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AdminTotalControllerTest {
//    @Spy
//    DataBase base;
//
//    @InjectMocks
//    AdminTotalController totalController;
//
//    @Test
//    public void deleteFormErrorCaseTest() {
//
//        int id = 202;
//        when(base.getProductById(id)).thenReturn(null);
//        ModelAndView view = totalController.deleteForm(id);
//
//        assertEquals(view.getViewName(), "error");
//        assertNotNull(view.getModel().get("message"));
//    }
//    @Test
//        public void deleteFormTest(){
//
//        int id=101;
//        ModelAndView view1 = totalController.deleteForm(id);
//
//        assertEquals(base.getProductById(id), null);
//        assertEquals(view1.getViewName(),"admin-total");
//        assertNotNull(view1.getModel().get("catalogue"));
//    }
//}
//
