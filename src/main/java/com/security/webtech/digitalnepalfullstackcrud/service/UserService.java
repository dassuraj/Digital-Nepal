package com.security.webtech.digitalnepalfullstackcrud.service;

import com.security.webtech.digitalnepalfullstackcrud.entity.User;
import com.security.webtech.digitalnepalfullstackcrud.exception.CustomRoleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findById(Long id);
    List<User> findAll();
    void saveUser(User user,String[] roles) throws CustomRoleNotFoundException;
    User updateUser(Long id,User user);
    void deleteUser(Long id);
    Optional<User> findByName(String name);
}
