package com.camilo.springbootplatzi.caseuse;

import com.camilo.springbootplatzi.entity.User;
import com.camilo.springbootplatzi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private final UserService userService;

    @Autowired
    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User user, Long userId) {
        return userService.update(user, userId);
    }
}
