package org.example.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
public class LoginDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
