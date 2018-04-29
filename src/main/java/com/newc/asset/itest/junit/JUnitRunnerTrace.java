package com.newc.asset.itest.junit;

/**
 * Created by paul on 2018/4/29.
 */
public class JUnitRunnerTrace {
    public static StackTraceElement guessJUnitTestCaseEntrance() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        boolean visitJUnit = false;
        boolean visitReflect = false;

        // Iterator trace stack
        for (int i=stackTraceElements.length-1; i>=0; i--) {
            StackTraceElement currentTrace = stackTraceElements[i];
            if (visitJUnit && visitReflect) {
                if (!currentTrace.getClassName().startsWith("sun.reflect."))
                    return currentTrace;
            }

            if (currentTrace.getClassName().startsWith("org.junit.")) {
                visitJUnit = true;
                continue;
            }

            if (currentTrace.getClassName().startsWith("sun.reflect.")) {
                visitReflect = true;
                continue;
            }
        }

        return null;
    }
}
