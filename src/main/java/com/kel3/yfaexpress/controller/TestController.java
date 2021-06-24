package com.kel3.yfaexpress.controller;

import com.kel3.yfaexpress.model.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(path = "/anonym")
    public DefaultResponse getToken(){
        return DefaultResponse.ok("Gak Perlu token");
    }

    @GetMapping(path = "/role")
    @RolesAllowed("ROLE_USER")
    public DefaultResponse getData(){
        System.out.println("mengambil");
        return DefaultResponse.ok("Perlu Token");
    }
}
