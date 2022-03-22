package com.camilo.springbootplatzi.repository;

import com.camilo.springbootplatzi.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // u es el alias del usuario
    // ?1 es el parametro
    @Query("select u from User u where u.email =?1")
    Optional<User> findByUserEmail(String userEmail);
    // otra manera de hacer la misma Query
    @Query
    Optional<User> findByEmail(String userEmail);
    // Selecciona todos los usuarios que inicen con un string dado por parametro
    // y los ordena de la manera en que yo quiera ya sea de forma descendente o ascendente
    @Query("select u from User u where u.name like ?1%")
    List<User> findAndSort(String userName, Sort sort);

    //Query methods
    List<User> findByNameAndEmail(String userName, String userEmail);
    List<User> findByName(String userName);

}
