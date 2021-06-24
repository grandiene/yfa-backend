package com.kel3.yfaexpress.mapper;


import com.kel3.yfaexpress.model.dto.RegisterDto;
import com.kel3.yfaexpress.model.dto.UserDto;
import com.kel3.yfaexpress.model.entity.Useraa;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public Useraa fromRegisterDto(RegisterDto dto) {
        Useraa user = new Useraa();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPhone(dto.getPhone());
        user.setKtp(dto.getKtp());
        user.setAlamat(dto.getAlamat());

        return user;
    }

    public RegisterDto toRegisterDto(Useraa user) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstname(user.getFirstname());
        registerDto.setLastname(user.getLastname());
        registerDto.setEmail(user.getEmail());
        registerDto.setUsername(user.getUsername());
        registerDto.setPhone(user.getPhone());
        registerDto.setKtp(user.getKtp());
        registerDto.setAlamat(user.getAlamat());
        return registerDto;
    }

    public UserDto toUserDto(Useraa user) {
        UserDto userDto = new UserDto();
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPhone(user.getPhone());
        userDto.setUserKeyId(user.getUserKeyId());
        userDto.setKtp(user.getKtp());
        userDto.setAlamat(user.getAlamat());
        return userDto;
    }
}
