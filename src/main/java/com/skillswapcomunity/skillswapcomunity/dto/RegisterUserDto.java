package com.skillswapcomunity.skillswapcomunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;
}