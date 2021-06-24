package com.kel3.yfaexpress.model.dto;
import lombok.Data;


@Data
public class UserDto extends CommonDto<Long>{
    private Integer idUser;
    private String firstname;
    private String lastname;
    private String phone;
    private String ktp;
    private String email;
    private String username;
    private String alamat;
    private String userKeyId;
}

