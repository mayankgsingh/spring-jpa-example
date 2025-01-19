package com.ms.example.countrydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.example.countrydb.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

}
