package com.example.alexlember.hello.quizz.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuizzControllerTest {

    QuizzController controller;

    @Before
    public void setUp() {
        controller = new QuizzController();
    }

    @Test
    public void initModelTest() {
        Assert.assertEquals(14, controller.getQuestionSize());
    }
}
