package com.kel3.yfaexpress.service;


import com.kel3.yfaexpress.model.dto.RegisterDto;
import com.kel3.yfaexpress.model.dto.UserDto;

public interface UserService {
    UserDto findByUsername(String username);
    public RegisterDto saveRegister(RegisterDto registerDto);
    public RegisterDto saveRegisterAdmin(RegisterDto registerDto);
}
