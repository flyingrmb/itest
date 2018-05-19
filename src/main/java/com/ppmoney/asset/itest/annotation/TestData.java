package com.ppmoney.asset.itest.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by paul on 2018/4/28.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestData {
    String value() default "";
    String node() default "";
}
