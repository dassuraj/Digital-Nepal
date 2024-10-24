package com.security.webtech.digitalnepalfullstackcrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;


import javax.persistence.*;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonBackReference
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs;






}
