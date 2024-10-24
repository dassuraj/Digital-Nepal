package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Role;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.RoleType;
import com.security.webtech.digitalnepalfullstackcrud.repository.RoleRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role update(Role role,Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
           roleRepository.deleteById(id);
    }

    @Override
    public Role findByName(RoleType role) {
        return roleRepository.findByName(role);
    }

}
