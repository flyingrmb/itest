package com.newc.asset.itest.data;

import com.newc.asset.itest.data.entity.Company;
import com.newc.asset.itest.data.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by paul on 2018/4/28.
 */
public class TestFileDataLoaderTest {
    @Test
    public void couldLoadTestCaseData() throws IOException {
        Map result = TestDataLoader.loadTestCaseData(Map.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.get("property1"), is("value1"));
    }

    @Test
    public void couldDeserializeTestCaseData() throws IOException {
        User user = TestDataLoader.loadTestCaseData(User.class, "user");
        assertThat(user, is(notNullValue()));
        assertThat(user.getName(), is("Paul Wong"));

        Company company = TestDataLoader.loadTestCaseData(Company.class, "company");
        assertThat(company, is(notNullValue()));
        assertThat(company.getName(), is("ppmoney"));
    }
}