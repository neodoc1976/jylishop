package org.george.jylishop.test.controllers;


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
