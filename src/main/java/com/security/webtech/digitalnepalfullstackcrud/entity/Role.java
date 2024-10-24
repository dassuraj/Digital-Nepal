package com.security.webtech.digitalnepalfullstackcrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "role")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(RoleType name) {
        this.name = name;
    }

}
