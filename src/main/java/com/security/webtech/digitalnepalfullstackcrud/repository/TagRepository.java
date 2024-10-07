package com.security.webtech.digitalnepalfullstackcrud.repository;

import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}
