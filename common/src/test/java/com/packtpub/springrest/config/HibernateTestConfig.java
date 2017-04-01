package com.packtpub.springrest.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:conf/application-test.properties")
@Import( { HibernateConfig.class} )
public class HibernateTestConfig {
    private static final Logger logger = Logger.getLogger(HibernateConfig.class);

    {
        logger.info("JPA Test Configuration loaded.");
    }
}
