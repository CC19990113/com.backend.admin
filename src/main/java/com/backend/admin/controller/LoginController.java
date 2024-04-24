package com.backend.admin.controller;

import com.backend.admin.dto.LoginDto;
import com.backend.admin.service.UserService;
import com.backend.admin.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
@Autowired
private UserService userService;
    @PostMapping ("/login")
    public Response login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    };
}
