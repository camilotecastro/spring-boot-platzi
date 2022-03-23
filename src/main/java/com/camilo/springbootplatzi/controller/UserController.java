package com.camilo.springbootplatzi.controller;

import com.camilo.springbootplatzi.caseuse.CreateUser;
import com.camilo.springbootplatzi.caseuse.DeleteUser;
import com.camilo.springbootplatzi.caseuse.GetUser;
import com.camilo.springbootplatzi.caseuse.UpdateUser;
import com.camilo.springbootplatzi.entity.User;
import com.camilo.springbootplatzi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final GetUser getUser;
    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final UpdateUser updateUser;
    private final UserRepository userRepository;

    @Autowired
    public UserController(GetUser getUser, CreateUser createUser,
                          DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping()
    ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity deleteUser(@PathVariable Long userId){
        deleteUser.remove(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}")
    ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId){
        return new ResponseEntity<>(updateUser.update(user, userId), HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getUsersPageable(@RequestParam int page, @RequestParam int size){
        return userRepository
                .findAll(PageRequest.of(page,size))
                .getContent();
    }


}
