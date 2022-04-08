package com.practicalbusiness.study.multiplesource.common.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:application-local.yml"})
@EnableJpaRepositories(
        basePackages = "com.practicalbusiness.study.multiplesource.dao",
        entityManagerFactoryRef = "clickhouseEntityManager",
        transactionManagerRef = "clickhouseTransactionManager"
)
public class ClickhouseConfiguration {

    @Autowired
    private Environment env;

    @Value("${spring.clickhouse-datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public LocalContainerEntityManagerFactoryBean clickhouseEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(clickhouseDatasource());
        em.setPersistenceUnitName("clickhouse");
        em.setPackagesToScan("com.practicalbusiness.study.multiplesource.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("spring.jpa.database-platform"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource clickhouseDatasource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(env.getProperty("spring.clickhouse-datasource.url"));
        dataSource.setUsername(env.getProperty("spring.clickhouse-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.clickhouse-datasource.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                clickhouseEntityManager().getObject());
        return transactionManager;
    }
}
