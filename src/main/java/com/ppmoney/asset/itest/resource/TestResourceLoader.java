package com.ppmoney.asset.itest.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppmoney.asset.itest.annotation.TestData;
import com.ppmoney.asset.itest.junit.JUnitRunnerTrace;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by paul on 2018/4/28.
 */
public class TestResourceLoader {
    private static final Log logger = LogFactory.getLog(TestResourceLoader.class);

    public static JsonNode getTestResource() throws IOException {
        TestDataIndicator indicator = createTestDataIndicator();
        Assert.notNull(indicator, "TestDataIndicator object should never be null.");

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource testResource = resourceLoader.getResource(indicator.getFileName());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(testResource.getFile());

        if (indicator.notSpecified()) return rootNode;

        return rootNode.path(indicator.getNodeName());
    }

    private static TestDataIndicator createTestDataIndicator() {
        TestDataIndicator indicator = createTestDataIndicatorByAnnotation();
        if (!indicator.isEmpty()) return indicator;

        return createTestDataIndicatorByClassName();
    }

    private static TestDataIndicator createTestDataIndicatorByAnnotation() {
        TestDataIndicator indicator = new TestDataIndicator();

        StackTraceElement stackTraceElement = JUnitRunnerTrace.guessJUnitTestCaseEntrance();

        Class<?> testClass = forName(stackTraceElement.getClassName());
        indicator = loadWithClassAnnotation(indicator, testClass);

        Method method = getTestCaseMethod(testClass, stackTraceElement.getMethodName());
        indicator = loadWithMethodAnnotation(indicator, method);

        return indicator;
    }

    private static Class<?> forName(String clazzName) {
        try {
            return ClassUtils.forName(clazzName, null);
        } catch (ClassNotFoundException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Fail to load class of " + clazzName, e);
            }
            return null;
        }
    }

    private static TestDataIndicator loadWithClassAnnotation(TestDataIndicator indicator, Class<?> testClass) {
        TestData testData = AnnotationUtils.findAnnotation(testClass, TestData.class);
        return loadWithTestDataAnnotation(indicator, testData);
    }


    private static Method getTestCaseMethod(Class<?> testClazz, String methodName) {
        try {
            return testClazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Fail to load test case method " + methodName, e);
            }

            return null;
        }
    }

    private static TestDataIndicator loadWithMethodAnnotation(TestDataIndicator indicator, Method method) {
        TestData testData = AnnotationUtils.getAnnotation(method, TestData.class);
        return loadWithTestDataAnnotation(indicator, testData);
    }

    private static TestDataIndicator loadWithTestDataAnnotation(TestDataIndicator indicator, TestData testData) {
        if (testData == null) return indicator;

        if (testData.value().length() != 0) indicator.setFileName(testData.value());
        if (testData.node().length() != 0) indicator.setNodeName(testData.node());

        return indicator;
    }


    private static TestDataIndicator createTestDataIndicatorByClassName() {
        TestDataIndicator indicator = new TestDataIndicator();

        StackTraceElement stackTraceElement = JUnitRunnerTrace.guessJUnitTestCaseEntrance();

        String location = stackTraceElement.getClassName().replaceAll("\\.", "/");
        String fileName =  "classpath:" + location + ".json";

        indicator.setFileName(fileName);
        indicator.setNodeName(stackTraceElement.getMethodName());

        return indicator;
    }

    @Data
    static class TestDataIndicator {
        private String fileName;
        private String nodeName;

        public boolean isEmpty() {
            return fileName == null || fileName.length() == 0;
        }

        public boolean notSpecified() {
            return nodeName == null || nodeName.length() == 0;
        }
    }
}
