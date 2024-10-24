package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Role;
import com.security.webtech.digitalnepalfullstackcrud.entity.User;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.RoleType;
import com.security.webtech.digitalnepalfullstackcrud.exception.CustomRoleNotFoundException;
import com.security.webtech.digitalnepalfullstackcrud.repository.UserRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.RoleService;
import com.security.webtech.digitalnepalfullstackcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user, String[] roleTypes) throws CustomRoleNotFoundException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roleTypes) {
            RoleType roleType = RoleType.valueOf(roleName);
            Role role = roleService.findByName(roleType);
            if (role == null) {
                role = new Role(roleType);
                roleService.save(role);
            }
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        boolean isExist = userRepository.existsById(id);
        if (isExist) {
            User user1 = userRepository.findById(id).get();
            user1.setUserName(user.getUserName());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setRoles(user.getRoles());
            return userRepository.save(user1);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByUserName(name);
    }
}
