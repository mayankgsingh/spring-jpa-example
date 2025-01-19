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
    basePackages = {"com.ms.example.countrydb.domain", "com.ms.example.countrydb.repository"},
    entityManagerFactoryRef = "countryEntityManagerFactory",
    transactionManagerRef = "countryTransactionManager"
    )
@EnableTransactionManagement
public class CountryDbConfig {
  private static final Logger log = LoggerFactory.getLogger(CountryDbConfig.class);
  
  @Bean
  public LocalContainerEntityManagerFactoryBean countryEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(countryDataSource());
    em.setPackagesToScan("com.ms.example.countrydb", "com.ms.example.countrydb.repository");
    em.setPersistenceUnitName("country");

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());

    return em;
  }

  @Bean
  public PlatformTransactionManager countryTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(countryEntityManagerFactory().getObject());

    return transactionManager;
  }

  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");

    return properties;
  }

  @Bean
  public DataSource countryDataSource() {
    final String dbFilePath = CountryDbConfig.class.getClassLoader().getResource("country.db").getFile();
    log.info("DB Path: {}", dbFilePath);
    
    final String countryDsUrl = String.format("jdbc:sqlite:%s", dbFilePath);
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.sqlite.JDBC");
    dataSource.setUrl(countryDsUrl);

    return dataSource;
  }
}
