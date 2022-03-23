package com.camilo.springbootplatzi.caseuse;

import com.camilo.springbootplatzi.entity.User;
import com.camilo.springbootplatzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private final UserService userService;

    @Autowired
    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User user) {
        return userService.save(user);
    }
}
