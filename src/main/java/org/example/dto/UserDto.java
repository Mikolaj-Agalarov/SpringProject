package org.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String role;

}