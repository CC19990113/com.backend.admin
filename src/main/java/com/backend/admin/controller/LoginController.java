package com.backend.admin.controller;

import com.backend.admin.dto.LoginDto;
import com.backend.admin.service.LoginService;
import com.backend.admin.utils.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {
@Resource
private LoginService loginService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Response login(@RequestBody LoginDto loginDto, HttpServletRequest request) {

        return loginService.login(loginDto,request);
    };
}
