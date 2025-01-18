package com.ms.example;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.ms.example.userdb",
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager"
    )
@EnableTransactionManagement
public class UserDbConfig {
  private static final Logger log = LoggerFactory.getLogger(UserDbConfig.class);
  
  @Bean
  public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(userDataSource());
    em.setPackagesToScan("com.ms.example.userdb");
    em.setPersistenceUnitName("user");

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());

    return em;
  }

  @Bean
  public PlatformTransactionManager userTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(userEntityManagerFactory().getObject());

    return transactionManager;
  }

  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");

    return properties;
  }

  @Bean
  public DataSource userDataSource() {
    final String dbFilePath = CountryDbConfig.class.getClassLoader().getResource("user.db").getFile();
    log.info("DB Path: {}", dbFilePath);
    
    final String countryDsUrl = String.format("jdbc:sqlite:%s", dbFilePath);
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.sqlite.JDBC");
    dataSource.setUrl(countryDsUrl);

    return dataSource;
  }
}
