package com.bleizing.sejuta_kebaikan_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bleizing.sejuta_kebaikan_be.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
