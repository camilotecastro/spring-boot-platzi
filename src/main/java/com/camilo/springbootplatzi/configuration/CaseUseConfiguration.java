package com.camilo.springbootplatzi.configuration;

import com.camilo.springbootplatzi.caseuse.GetUser;
import com.camilo.springbootplatzi.caseuse.GetUserImpl;
import com.camilo.springbootplatzi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImpl(userService);
    }
}
