package org.george.jylishop.test.controllers;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Yulya on 29.05.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ProductControllerTest.class,
        ContactControllerTest.class,
        AdminContactControllerTest.class,
        AdminContactControllerForEditModeTest.class,
        AdminGelControllerTest.class,
        AdminHemoControllerTest.class})

public class ControllerTestSuit {


}
