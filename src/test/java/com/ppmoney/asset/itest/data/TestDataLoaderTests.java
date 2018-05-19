package com.ppmoney.asset.itest.data;

import com.ppmoney.asset.itest.data.entity.Company;
import com.ppmoney.asset.itest.data.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by paul on 2018/4/28.
 */
public class TestDataLoaderTests {
    @Test
    public void couldLoadTestCaseData() throws IOException {
        Map result = TestDataLoader.loadTestData(Map.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.get("property1"), is("value1"));
    }

    @Test
    public void couldDeserializeTestCaseData() throws IOException {
        User user = TestDataLoader.loadTestData(User.class, "user");
        assertThat(user, is(notNullValue()));
        assertThat(user.getName(), is("Paul Wong"));

        Company company = TestDataLoader.loadTestData(Company.class, "company");
        assertThat(company, is(notNullValue()));
        assertThat(company.getName(), is("ppmoney"));
    }
}