package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import com.security.webtech.digitalnepalfullstackcrud.repository.TagRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag, Long id) {
       boolean isExist= tagRepository.existsById(id);
       if(isExist){
           Tag tag1=tagRepository.findById(id).get();
           tag1.setName(tag.getName());
         return tagRepository.save(tag1);
       }else {
           return null;
       }
    }

    @Override
    public void delete(Long id) {
     tagRepository.deleteById(id);
    }
}
