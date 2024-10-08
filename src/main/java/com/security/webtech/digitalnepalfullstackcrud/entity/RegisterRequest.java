package com.security.webtech.digitalnepalfullstackcrud.entity;

import com.security.webtech.digitalnepalfullstackcrud.enumtype.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private ERole role;

}
