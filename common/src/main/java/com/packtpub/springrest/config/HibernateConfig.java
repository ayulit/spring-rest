package com.packtpub.springrest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // JPA feature
public class HibernateConfig {

    private static final Logger logger = Logger.getLogger(HibernateConfig.class);

    @Autowired
    private Environment environment;

    {
        logger.info("JPA Configuration loaded.");
    }

    @Bean
    public JpaTransactionManager transactionManager() {
      JpaTransactionManager jtManager = new JpaTransactionManager();
      jtManager.setEntityManagerFactory(entityManagerFactory().getObject());
      return jtManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
      factoryBean.setDataSource(dataSource());

      // JPA features...?
      JpaVendorAdapter hibernateVendorAdapter = new HibernateJpaVendorAdapter();
      factoryBean.setJpaVendorAdapter(hibernateVendorAdapter);

      String[] packages = { "com.packtpub.springrest.model" };
      factoryBean.setPackagesToScan(packages);
      factoryBean.setJpaProperties(getJPAProperties());
      return factoryBean;
    }

    private Properties getJPAProperties() {

        final Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        // xlitand: 
        // Drop and re-create the database schema on startup,
        // -create: every time
        // -update: if ONLY model changed!
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

//        properties.setProperty("hibernate.format_sql", "true");

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
