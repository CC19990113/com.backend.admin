package com.backend.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.backend.admin.dto.PageDto;
import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login_logs")
public class LoginLogsController {
    @Autowired
    private LoginLogsService LoginLogsService;

    @SaCheckLogin
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Response list(@RequestBody PageDto pageDto){
        return LoginLogsService.getList(pageDto);
    }
}
