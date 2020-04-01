package com.example.defensefromxss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public Map<String,Object> loginTheFrist(String username, String password){
        Map<String,Object> map = new HashMap<>();
        System.out.println("----------进来了---------");
        System.out.println(username);
        System.out.println(password);
        map.put("username",username);
        map.put("password",password);
        return map;
    }
}
