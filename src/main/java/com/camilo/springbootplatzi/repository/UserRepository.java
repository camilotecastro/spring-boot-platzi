package com.camilo.springbootplatzi.repository;

import com.camilo.springbootplatzi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // u es el alias del usuario
    // ?1 es el parametro
    @Query("select u from User u where u.email =?1")
    Optional<User> findByUserEmail(String userEmail);
}
