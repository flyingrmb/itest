package com.ppmoney.asset.itest.jackson;

import com.ppmoney.asset.itest.jackson.src.Address;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by paul on 2018/4/28.ß
 */
public class LombokTest {
    @Test
    public void couldIgnoreSetterAndGetter() {
        Address address = new Address();
        address.setCity("Shanghai");
        assertThat(address.getCity(), is("Shanghai"));
    }
}