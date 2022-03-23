package com.camilo.springbootplatzi.caseuse;

import com.camilo.springbootplatzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private final UserService userService;

    @Autowired
    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long userId) {
        userService.delete(userId);
    }
}
