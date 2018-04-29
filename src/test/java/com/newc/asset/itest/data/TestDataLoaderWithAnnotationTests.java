package com.newc.asset.itest.data;

import com.alibaba.fastjson.JSONObject;
import com.newc.asset.itest.annotation.TestData;
import com.newc.asset.itest.data.entity.Company;
import com.newc.asset.itest.data.entity.User;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * Created by paul on 2018/4/28.
 */
@TestData("classpath:data/test_data.json")
public class TestDataLoaderWithAnnotationTests {
    @Test
    public void loadTestDataWithClassAnnotation() throws IOException {
        JSONObject result = TestDataLoader.loadTestData(JSONObject.class);
        assertThat(result, is(notNullValue()));

        JSONObject simpleData = result.getJSONObject("simpleData");
        assertThat(simpleData, is(notNullValue()));

        assertThat(simpleData.getString("property1"), is("value1"));
    }

    @Test
    @TestData("classpath:data/method_test_data.json")
    public void overrideTestDataWithMethodAnnotation() throws IOException {
        JSONObject result = TestDataLoader.loadTestData(JSONObject.class);
        assertThat(result, is(notNullValue()));

        String fileName = result.getString("fileName");
        assertThat(fileName, is("method_test_data"));
    }

    @Test
    @TestData(node = "deserializeNode")
    public void loadTestCaseDataWithClassAnnotation() throws IOException {
        JSONObject result = TestDataLoader.loadTestData(JSONObject.class);
        assertThat(result, is(notNullValue()));

        String information = result.getString("information");
        assertThat(information, is("test_data_node"));
    }

    @Test
    @TestData(value = "classpath:data/method_test_data.json", node = "deserializeNode")
    public void loadTestCaseDataWithMethodAnnotation() throws IOException {
        JSONObject result = TestDataLoader.loadTestData(JSONObject.class);
        assertThat(result, is(notNullValue()));

        String information = result.getString("information");
        assertThat(information, is("method_test_data_node"));

        User user = TestDataLoader.loadTestData(User.class, "user");
        assertThat(user, is(notNullValue()));
        assertThat(user.getName(), is("Paul Wong(method)"));

        Company company = TestDataLoader.loadTestData(Company.class, "company");
        assertThat(company, is(notNullValue()));
        assertThat(company.getName(), is("ppmoney(method)"));
    }
}
