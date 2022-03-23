package com.camilo.springbootplatzi.caseuse;

import com.camilo.springbootplatzi.entity.User;
import com.camilo.springbootplatzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GetUserImpl implements GetUser {
    private final UserService userService;

    @Autowired
    public GetUserImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
