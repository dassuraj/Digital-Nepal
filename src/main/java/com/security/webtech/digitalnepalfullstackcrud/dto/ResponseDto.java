package com.security.webtech.digitalnepalfullstackcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
    private String message;
    private HttpStatus status;
    private Object object;

}
