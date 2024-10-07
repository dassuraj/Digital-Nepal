package com.security.webtech.digitalnepalfullstackcrud.repository;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
}
