package com.packtpub.springrest.inventory.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.packtpub.springrest.config.HibernateConfig;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.inventory.impl.InventoryServiceImpl;

@Configuration
@PropertySource("classpath:conf/application-test.properties")

@Import( {InventoryConfig.class} )
public class InventoryTestConfig  {
    
    private static final Logger logger = Logger.getLogger(HibernateConfig.class);

    {
        logger.info("Inventory Test Configuration loaded.");
    }
    
    @Bean
    public InventoryService getInventoryService() {
        return new InventoryServiceImpl();
    }
    
}
