package com.ppmoney.asset.itest.junit;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by paul on 2018/4/29.
 */
public class JUnitRunnerTraceTest {
    @Test
    public void calculateJUnitRunnerEntrance() {
        StackTraceElement stackTraceElement = JUnitRunnerTrace.guessJUnitTestCaseEntrance();
        assertThat(stackTraceElement, is(notNullValue()));

        assertTrue(stackTraceElement.getClassName().contains(JUnitRunnerTraceTest.class.getSimpleName()));
    }
}