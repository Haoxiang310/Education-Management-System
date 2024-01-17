package com.ems902;

import com.ems902.Controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
public class ControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void testAdmin(){
        String res = userController.queryAllCourseScore("c++");
        System.out.println(res);
    }
}
