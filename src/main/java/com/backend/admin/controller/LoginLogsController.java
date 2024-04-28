package com.backend.admin.controller;

import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login_logs")
public class LoginLogsController {
    @Autowired
    private LoginLogsService LoginLogsService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Response list(){
        return LoginLogsService.getList();
    }
}
