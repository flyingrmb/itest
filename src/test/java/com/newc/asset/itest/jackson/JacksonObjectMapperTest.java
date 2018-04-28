package com.newc.asset.itest.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newc.asset.itest.jackson.src.Employee;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by paul on 2018/4/28.
 */
public class JacksonObjectMapperTest {
    @Test
    public void couldCreateEmployeeByJackson() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:jackson/employee.json");

        ObjectMapper objectMapper = new ObjectMapper();

        Employee emp = objectMapper.readValue(resource.getFile(), Employee.class);
        assertThat(emp, is(notNullValue()));

        assertThat(emp.getName(), is("Pankaj"));
    }
}
