package com.camilo.springbootplatzi.service;

import com.camilo.springbootplatzi.entity.User;
import com.camilo.springbootplatzi.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Log log = LogFactory.getLog(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional //Permite hacer rollback de las transacciones en caso de un error
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> log.info("Insert: " + user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public User update(User userUpdate, Long userId) {
        return userRepository.findById(userId)
                .map(
                        user -> {
                            user.setEmail(userUpdate.getEmail());
                            user.setName(userUpdate.getName());
                            user.setBirthDate(userUpdate.getBirthDate());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
