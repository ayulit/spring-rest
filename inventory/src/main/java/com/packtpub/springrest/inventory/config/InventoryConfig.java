package com.packtpub.springrest.inventory.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.packtpub.springrest.config.HibernateConfig;

// TODO enable in case of using Java class annotation style config with JPA
//@Configuration
//@PropertySource("classpath:conf/application.properties")
//@Import( { HibernateConfig.class} )
public class InventoryConfig {
    
    private static final Logger logger = Logger.getLogger(HibernateConfig.class);

    {
        logger.info("Inventory Configuration loaded.");
    }
}
