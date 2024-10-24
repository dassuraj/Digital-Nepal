package com.security.webtech.digitalnepalfullstackcrud.service;

import com.security.webtech.digitalnepalfullstackcrud.entity.Role;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.RoleType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Optional<Role> findById(Long id);
    List<Role> findAll();
    void save(Role role);
    Role update(Role role,Long id);
    void delete(Long id);
    Role findByName(RoleType role);
}
