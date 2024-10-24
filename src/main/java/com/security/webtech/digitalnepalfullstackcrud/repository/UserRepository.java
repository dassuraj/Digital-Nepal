package com.security.webtech.digitalnepalfullstackcrud.repository;

import com.security.webtech.digitalnepalfullstackcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
