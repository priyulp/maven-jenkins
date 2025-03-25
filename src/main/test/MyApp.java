package com.vinaysdevopslab.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void testHomeEndpoint() {
        String result = homeController.home();
        assertEquals("Welcome to VinayDevOpsLab!", result);
    }
}
