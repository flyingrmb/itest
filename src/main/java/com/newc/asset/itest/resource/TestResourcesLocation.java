package com.newc.asset.itest.resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * Created by paul on 2018/4/28.
 */
public class TestResourcesLocation {
    public static Resource getTestResource(String clazzName) {
        Assert.notNull(clazzName, "Class should not be null.");

        String resourceLocation = getResourceLocation(clazzName);
        return new DefaultResourceLoader().getResource(resourceLocation);
    }

    public static String getResourceLocation(String clazzName) {
        Assert.notNull(clazzName, "Class name should not be null.");
        String location = clazzName.replaceAll("\\.", "/");
        return "classpath:" + location + ".json";
    }
}
