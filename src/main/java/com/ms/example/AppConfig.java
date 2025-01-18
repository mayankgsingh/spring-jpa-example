package com.ms.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ms.example")
public class AppConfig {
  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
  
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, UserDbConfig.class, CountryDbConfig.class);
    ctx.start();
    log.info("Context started...");
    
    DbService dbService = ctx.getBean(DbService.class);
    log.info("Query user db...");
    dbService.getUsers();

    log.info("Query country db...");
    dbService.getCountries();
    
    log.info("User by id: {}", dbService.getUserUsingRepository(1));
    
    log.info("Closing context...");
    ctx.close();
    
    log.info("Done.");
  }
}
