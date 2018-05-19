package com.ppmoney.asset.itest.data;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppmoney.asset.itest.resource.TestResourceLoader;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created by paul on 2018/4/28.
 */
public class TestDataLoader {
    public static <T> T loadTestData(Class<T> type) throws IOException {
        Assert.notNull(type, "Type should not be null.");

        JsonNode resource = TestResourceLoader.getTestResource();
        return new ObjectMapper().treeToValue(resource, type);
    }

    public static <T> T loadTestData(Class<T> type, String node) throws IOException {
        Assert.notNull(type, "Type should not be null.");

        JsonNode resource = TestResourceLoader.getTestResource();
        JsonNode targetNode = resource.path(node);
        Assert.isTrue(!targetNode.isMissingNode(), "No node of "+ node +", Please check the test data's format.");

        return new ObjectMapper().treeToValue(targetNode, type);
    }
}
