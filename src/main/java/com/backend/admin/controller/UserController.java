package com.backend.admin.controller;

import cn.dev33.satoken.util.SaResult;
import com.backend.admin.dto.LoginDto;
import com.backend.admin.service.UserService;
import com.backend.admin.utils.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class UserController {
@Resource
private UserService userService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Response login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return userService.login(loginDto,request);
    };
}
