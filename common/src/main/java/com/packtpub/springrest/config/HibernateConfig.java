package com.packtpub.springrest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {

    private static final Logger logger = Logger.getLogger(HibernateConfig.class);

    @Autowired
    private Environment environment;

    {
        logger.info("Hibernate Configuration loaded.");
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();		  
        txManager.setSessionFactory(sessionFactory().getObject());	  
        return txManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory  = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        String[] packages = { "com.packtpub.springrest.model" };
        sessionFactory.setPackagesToScan(packages);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {

        final Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        // xlitand: 
        // Drop and re-create the database schema on startup,
        // -create: every time
        // -update: if ONLY model changed!
        properties.setProperty("hibernate.hbm2ddl.auto", "create");

        return properties;
    }

    private DataSource dataSource() {

        BasicDataSource ds = new BasicDataSource();

        ds.setUrl(environment.getRequiredProperty("jdbc.url"));		
        ds.setUsername(environment.getRequiredProperty("jdbc.username"));
        ds.setPassword(environment.getRequiredProperty("jdbc.password"));
        ds.setMaxTotal(Integer.valueOf(environment.getRequiredProperty("jdbc.maxConnections")));
        ds.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));

        return ds;
    }

}
