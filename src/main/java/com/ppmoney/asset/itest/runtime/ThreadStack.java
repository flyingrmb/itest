package com.ppmoney.asset.itest.runtime;

import org.springframework.util.Assert;

/**
 * Created by paul on 2018/4/28.
 */
public class ThreadStack {
    public static StackTraceElement getInvoker() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Assert.notNull(stackTraceElements, "Stack trace should not be null.");
        Assert.isTrue(stackTraceElements.length >= 4, "Stack size is not correct!");

        return stackTraceElements[3];
    }
}
