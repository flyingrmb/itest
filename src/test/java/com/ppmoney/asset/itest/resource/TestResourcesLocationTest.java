package com.ppmoney.asset.itest.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.ppmoney.asset.itest.annotation.TestData;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by paul on 2018/4/28.
 */
@TestData("classpath:data/test_data.json")
public class TestResourcesLocationTest {
    @Test
    public void loadTestResourcesWithClassAnnotation() throws IOException {
        JsonNode resource = TestResourceLoader.getTestResource();
        assertThat(resource, is(notNullValue()));
    }


    @Test
    @TestData("classpath:data/method_test_data.json")
    public void overrideClassAnnotationWithMethodAnnotation() throws IOException {
        JsonNode resource = TestResourceLoader.getTestResource();
        assertThat(resource, is(notNullValue()));
    }
}