package com.packtpub.springrest.inventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.packtpub.springrest.config.HibernateConfig;

@Configuration
@PropertySource("classpath:conf/application.properties")
@Import( { HibernateConfig.class} )
public class InventoryConfig {

}
