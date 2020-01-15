package com.epiuse.artifact;


import com.epiuse.datasource.DbContextHolder;
import com.epiuse.datasource.RoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class DBConfiguration {

    @Autowired
    private Environment env;


    @Bean
    public DataSource defaultDataSource() {

        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> map = new HashMap<>();

        //TODO: Get this from Config Database
        map.put("vantage", DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/vantage?useSSL=false").username("root").password("xpedia023").build());
        map.put("enterprise", DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/enterprise?useSSL=false").username("root").password("xpedia023").build());

        routingDataSource.setTargetDataSources(map);
        routingDataSource.setDefaultTargetDataSource(map.get("vantage"));
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        //Lookup DB here

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(DbContextHolder.getDbType().JPAPackage);
        factory.setDataSource(defaultDataSource());

        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.persistent.database-platform"));
        properties.put("hibernate.jdbc.batch_size", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
        properties.put("hibernate.order_inserts", env.getProperty("spring.jpa.properties.hibernate.order_inserts"));
        properties.put("hibernate.order_updates", env.getProperty("spring.jpa.properties.hibernate.order_updates"));
        properties.put("hibernate.jdbc.batch_versioned_data", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_versioned_data"));
        properties.put("hibernate.generate_statistics", env.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
        properties.put("hibernate.id.new_generator_mappings", env.getProperty("spring.jpa.properties.hibernate.id.new_generator_mappings"));
        properties.put("hhibernate.cache.use_second_level_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.globally_quoted_identifiers", env.getProperty("spring.jpa.properties.hibernate.globally_quoted_identifiers"));
        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        properties.put("hibernate.use_sql_comments", env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
        properties.put("hibernate.type", env.getProperty("spring.jpa.properties.hibernate.type"));
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"));
        properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
        factory.setJpaPropertyMap(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }



//    @Autowired
//    private Environment env;
//
//    public DBConfiguration() {
//        super();
//    }
//
//    @Autowired
//    private PlatformTransactionManager transactionManager; // Hierdie is belangrik
//
//    public DataSource defaultDataSource() {
//
//        RoutingDataSource routingDataSource = new RoutingDataSource();
//        Map<Object, Object> map = new HashMap<>();
//
//        //TODO: Get this from Config Database
//        map.put("vantage", DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/vantage?useSSL=false").username("root").password("xpedia023").build());
//        map.put("enterprise", DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/enterprise?useSSL=false").username("root").password("xpedia023").build());
//
//        routingDataSource.setTargetDataSources(map);
//        routingDataSource.setDefaultTargetDataSource(map.get("vantage"));
//        routingDataSource.afterPropertiesSet();
//
//        return routingDataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean persistentDatabaseEntityManager() {
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/vantage?useSSL=false").username("root").password("xpedia023").build());
//        em.setPackagesToScan("com.epiuse.models");
//
//        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        final HashMap<String, Object> properties = new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("hibernate.dialect", env.getProperty("spring.jpa.persistent.database-platform"));
//        properties.put("hibernate.jdbc.batch_size", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
//        properties.put("hibernate.order_inserts", env.getProperty("spring.jpa.properties.hibernate.order_inserts"));
//        properties.put("hibernate.order_updates", env.getProperty("spring.jpa.properties.hibernate.order_updates"));
//        properties.put("hibernate.jdbc.batch_versioned_data", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_versioned_data"));
//        properties.put("hibernate.generate_statistics", env.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
//        properties.put("hibernate.id.new_generator_mappings", env.getProperty("spring.jpa.properties.hibernate.id.new_generator_mappings"));
//        properties.put("hhibernate.cache.use_second_level_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_second_level_cache"));
//        properties.put("hibernate.globally_quoted_identifiers", env.getProperty("spring.jpa.properties.hibernate.globally_quoted_identifiers"));
//        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
//        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
//        properties.put("hibernate.use_sql_comments", env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
//        properties.put("hibernate.type", env.getProperty("spring.jpa.properties.hibernate.type"));
//        properties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"));
//        properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
////    @Bean
////    public DataSource persistentDataSource() {
////        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName(env.getProperty("spring.persistent.datasource.driverClassName"));
////        dataSource.setUrl(env.getProperty("spring.persistent.datasource.url"));
////        dataSource.setUsername(env.getProperty("spring.persistent.datasource.username"));
////        dataSource.setPassword(env.getProperty("spring.persistent.datasource.password"));
////
////        return dataSource;
////    }
//
//    @Bean
//    public PlatformTransactionManager persistentDatabaseTransactionManager() {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(persistentDatabaseEntityManager().getObject());
//        return transactionManager;
//    }

}
