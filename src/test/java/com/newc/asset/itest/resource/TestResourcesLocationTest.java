package com.newc.asset.itest.resource;

import org.junit.Test;
import org.springframework.core.io.Resource;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by paul on 2018/4/28.
 */
public class TestResourcesLocationTest {
    @Test
    public void couldLoadTestResources() {
        Resource resource = TestResourcesLocation.getTestResource(this.getClass().getName());
        assertThat(resource, is(notNullValue()));
    }
}