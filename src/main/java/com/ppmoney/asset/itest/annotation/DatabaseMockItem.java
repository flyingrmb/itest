package com.ppmoney.asset.itest.annotation;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by paul on 2018/5/19.
 */
public @interface DatabaseMockItem {
    String file();
    Class<? extends CrudRepository> repository();
}
