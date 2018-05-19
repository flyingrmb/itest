package com.ppmoney.asset.itest.jackson.src;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by paul on 2018/4/28.
 */
@Data
public class Employee {
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private long[] phoneNumbers;
    private String role;
    private List<String> cities;
    private Map<String, String> properties;
}
