package com.ms.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ms.example.countrydb.Country;
import com.ms.example.userdb.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class DbService {
  private static final Logger log = LoggerFactory.getLogger(DbService.class);
  
  @PersistenceContext(unitName = "user")
  private EntityManager emUser;
  
  @PersistenceContext(unitName = "country")
  private EntityManager emCountry;
  
  public List<User> getUsers() {
    CriteriaBuilder cb = emUser.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    cq.from(User.class);
    
    TypedQuery<User> tq = emUser.createQuery(cq);
    List<User> users = tq.getResultList();
    log.info("Users: {}", users);
    
    return users;
  }
  
  public List<Country> getCountries() {
    CriteriaBuilder cb = emCountry.getCriteriaBuilder();
    CriteriaQuery<Country> cq = cb.createQuery(Country.class);
    cq.from(Country.class);
    
    TypedQuery<Country> tq = emCountry.createQuery(cq);
    List<Country> countries = tq.getResultList();
    log.info("Country: {}", countries);
    return countries;
  }
}
