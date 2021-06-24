package com.kel3.yfaexpress.service.impl;

import com.kel3.yfaexpress.configuration.Exception.CommonException;
import com.kel3.yfaexpress.mapper.UserMapper;
import com.kel3.yfaexpress.model.dto.RegisterDto;
import com.kel3.yfaexpress.model.dto.UserDto;
import com.kel3.yfaexpress.model.entity.Useraa;
import com.kel3.yfaexpress.repository.UserRepository;
import com.kel3.yfaexpress.service.UserService;
import com.kel3.yfaexpress.webclient.KeyCloakWebClient;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeyCloakWebClient keyCloakWebClient;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper, KeyCloakWebClient keyCloakWebClient) {
        this.userRepository = repository;
        this.userMapper = userMapper;
        this.keyCloakWebClient = keyCloakWebClient;
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toUserDto(userRepository.findByUsername(username).get());
    }

    @Override
    public RegisterDto saveRegister(RegisterDto registerDto) {
        //lower case email
        registerDto.setEmail(registerDto.getEmail().toLowerCase());
        Useraa user = userMapper.fromRegisterDto(registerDto);
        user.setCreatedBy(registerDto.getEmail());
        user.setCreatedOn(new Date());

        Response response = keyCloakWebClient.doRegister(registerDto);
        if (response.getStatus() == HttpStatus.CREATED.value()) {
            try {
                String userId = CreatedResponseUtil.getCreatedId(response);
                user.setUserKeyId(userId);
                registerDto = userMapper.toRegisterDto(userRepository.save(user));
                System.out.println(registerDto);
                return registerDto;
            } catch (Exception e) {
                keyCloakWebClient.deleteUser(response);
                throw new RuntimeException(e);
            }
        } else {
            throw new CommonException("Gagal Register");

        }
    }

    @Override
    public RegisterDto saveRegisterAdmin(RegisterDto registerDto) {
        //lower case email
        registerDto.setEmail(registerDto.getEmail().toLowerCase());
        Useraa user = userMapper.fromRegisterDto(registerDto);
        user.setCreatedBy(registerDto.getEmail());
        user.setCreatedOn(new Date());

        Response response = keyCloakWebClient.doRegisterAdmin(registerDto);
        if (response.getStatus() == HttpStatus.CREATED.value()) {
            try {
                String userId = CreatedResponseUtil.getCreatedId(response);
                user.setUserKeyId(userId);
                registerDto = userMapper.toRegisterDto(userRepository.save(user));
                return registerDto;
            } catch (Exception e) {
                keyCloakWebClient.deleteUser(response);
                throw new RuntimeException(e);
            }
        } else {
            throw new CommonException("Gagal Register");

        }
    }

}
