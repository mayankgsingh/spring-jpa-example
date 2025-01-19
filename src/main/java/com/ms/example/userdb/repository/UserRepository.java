package com.ms.example.userdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.example.userdb.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
