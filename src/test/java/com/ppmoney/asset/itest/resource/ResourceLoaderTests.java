package com.ppmoney.asset.itest.resource;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by paul on 2018/4/28.
 */
public class ResourceLoaderTests {
    @Test
    public void couldLoadResourcesWithSpringResourceLoader() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:itest.properties");
        ResourcePropertySource propertySource = new ResourcePropertySource(resource);
        Object value = propertySource.getProperty("property1");
        assertThat(value, is("value1"));
    }
}
