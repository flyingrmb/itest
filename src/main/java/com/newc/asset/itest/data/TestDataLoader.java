package com.newc.asset.itest.data;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newc.asset.itest.resource.TestResourcesLocation;
import com.newc.asset.itest.runtime.ThreadStack;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created by paul on 2018/4/28.
 */
public class TestDataLoader {
    public static <T> T loadClassData(Class<T> type) throws IOException {
        Assert.notNull(type, "Type should not be null.");

        StackTraceElement stackTraceElement = ThreadStack.getInvoker();
        Resource resource = TestResourcesLocation.getTestResource(stackTraceElement.getClassName());

        return new ObjectMapper().readValue(resource.getFile(), type);
    }

    public static <T> T loadTestCaseData(Class<T> type, String node) throws IOException {
        if (node == null || node.length() == 0) return loadTestCaseData(type);

        Assert.notNull(type, "Type should not be null.");

        StackTraceElement stackTraceElement = ThreadStack.getInvoker();
        JsonNode testCaseNode = getMethodNode(stackTraceElement.getClassName(), stackTraceElement.getMethodName());

        JsonNode targetNode = testCaseNode.path(node);
        Assert.isTrue(!targetNode.isMissingNode(), "Please check the test data's format.");

        return new ObjectMapper().treeToValue(targetNode, type);
    }

    public static <T> T loadTestCaseData(Class<T> type) throws IOException {
        Assert.notNull(type, "Type should not be null.");

        StackTraceElement stackTraceElement = ThreadStack.getInvoker();
        JsonNode testCaseNode = getMethodNode(stackTraceElement.getClassName(), stackTraceElement.getMethodName());

        return new ObjectMapper().treeToValue(testCaseNode, type);
    }

    private static JsonNode getMethodNode(String className, String methodName) throws IOException {
        Resource resource = TestResourcesLocation.getTestResource(className);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(resource.getFile());

        JsonNode testCaseNode = rootNode.path(methodName);
        Assert.isTrue(!testCaseNode.isMissingNode(), "Please check the test data of [" +
                className + "-"+
                methodName +"]");

        return testCaseNode;
    }
}
