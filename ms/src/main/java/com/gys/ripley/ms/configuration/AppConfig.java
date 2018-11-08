package com.gys.ripley.ms.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.gys.ripley.ms.services.impl" })
public class AppConfig {

}
