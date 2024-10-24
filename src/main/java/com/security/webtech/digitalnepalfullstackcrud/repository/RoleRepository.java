package com.security.webtech.digitalnepalfullstackcrud.repository;

import com.security.webtech.digitalnepalfullstackcrud.entity.Role;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
   Role findByName(RoleType name);
}
