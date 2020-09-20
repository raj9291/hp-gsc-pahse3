package com.sj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
@ComponentScan(basePackages = { "com.sj.service", "com.sj.resources" })
public class RootConfig {

}
