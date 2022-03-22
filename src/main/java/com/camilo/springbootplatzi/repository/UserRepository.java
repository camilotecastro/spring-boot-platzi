package com.camilo.springbootplatzi.repository;

import com.camilo.springbootplatzi.dto.UserDto;
import com.camilo.springbootplatzi.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    Optional<User> findByNameAndEmail(String userName, String userEmail);
    List<User> findByName(String userName);

    //JPQL con named parameters ademas utilizando una clase DTO
    @Query("SELECT new com.camilo.springbootplatzi.dto.UserDto(u.id, u.name, u.birthDate)" +
            "FROM User u " +
            "WHERE u.birthDate=:parametroFecha " +
            "AND u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                   @Param("parametroEmail") String email);

}
