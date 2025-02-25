package com.ms.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ms.example.countrydb.domain.Country;
import com.ms.example.userdb.domain.User;

@SpringJUnitConfig(classes = TestConfig.class)
public class DbServiceTest {

  private static final Logger log = LoggerFactory.getLogger(DbServiceTest.class);
  
  @Autowired
  private DbService dbService;
  
  @Autowired 
  private ApplicationContext ctx;
  
  @Test
  public void testCountryDbCall() {
    log.info("Context: {}", ctx);
    assertNotNull(ctx, "Spring context is null");
    assertNotNull(dbService, "Db service is null");
    
    List<Country> list = dbService.getCountries();
    assertNotNull(list, "Countries list is null.");
    assertEquals(2, list.size());
    
  }
  
  @Test
  public void testUserDbCall() {
    log.info("Context: {}", ctx);
    assertNotNull(ctx, "Spring context is null");
    assertNotNull(dbService, "Db service is null");
    
    List<User> list = dbService.getUsers();
    assertNotNull(list, "User list is null.");
    assertEquals(2, list.size());
  }
  
  @Test
  public void testUserById() {
    log.info("Context: {}", ctx);
    assertNotNull(ctx, "Spring context is null");
    assertNotNull(dbService, "Db service is null");
    
    User user = dbService.getUserUsingRepository(1);
    assertNotNull(user, "User is null.");
    assertEquals("USER1", user.getName());
    log.info("User: {}", user);
  }
  
  @Test
  public void testCountryById() {
    log.info("Context: {}", ctx);
    assertNotNull(ctx, "Spring context is null");
    assertNotNull(dbService, "Db service is null");
    
    Country country = dbService.getCountryUsingRepository(1);
    assertNotNull(country, "Country is null.");
    assertEquals("AUSTRALIA", country.getName());
    log.info("User: {}", country);
  }
  
}
