package com.ppmoney.asset.itest.config;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.util.Properties;

/**
 * Created by paul on 2018/5/20.
 */
@Setter
public class EntityManagerFactoryBean {
    private DataSource dataSource;
    private JpaVendorAdapter jpaVendorAdapter;

    @NotNull
    private String packagesToScan;
    private String formatSql;
    private String showSql;
    private String hbm2ddlAuto;

    public EntityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        this.dataSource = dataSource;
        this.jpaVendorAdapter = jpaVendorAdapter;
    }

    @Nullable
    public EntityManagerFactory getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPackagesToScan(packagesToScan);
        factory.setJpaProperties(getHibernateProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        if (formatSql != null) {
            properties.put("hibernate.format_sql", formatSql);
        }

        if (showSql != null) {
            properties.put("hibernate.show_sql", showSql);
        }

        if (hbm2ddlAuto != null) {
            properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        }

        return properties;
    }
}
