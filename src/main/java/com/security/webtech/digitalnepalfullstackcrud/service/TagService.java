package com.security.webtech.digitalnepalfullstackcrud.service;

import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    public List<Tag> findAll();
    public Tag findById(Long id);
    public Tag save(Tag tag);
    public Tag update(Tag tag,Long id);
    public void delete(Long id);

}