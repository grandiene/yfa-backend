package com.kel3.yfaexpress.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
//    @NotBlank
    private String firstname;
    private String lastname;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @NotBlank
    private String ktp;
    private String alamat;
}
