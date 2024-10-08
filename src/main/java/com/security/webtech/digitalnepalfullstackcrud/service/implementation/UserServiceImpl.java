package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Role;
import com.security.webtech.digitalnepalfullstackcrud.entity.User;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.ERole;
import com.security.webtech.digitalnepalfullstackcrud.repository.RoleRepository;
import com.security.webtech.digitalnepalfullstackcrud.repository.UserRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;
    @Override
    public User registerUser(String username, String password, ERole role) {
        User user=new User();
        user.setUserName(username);
        user.setPassword(encoder.encode(password));
        Role userRole =roleRepository.findByName(role).
                orElseThrow(()->new RuntimeException("Role Not Found"));
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }
}
