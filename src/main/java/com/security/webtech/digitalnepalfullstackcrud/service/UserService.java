package com.security.webtech.digitalnepalfullstackcrud.service;

import com.security.webtech.digitalnepalfullstackcrud.entity.User;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.ERole;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User registerUser(String username, String password, ERole role);
}
