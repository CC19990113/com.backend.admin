package com.backend.admin.controller;

import com.backend.admin.dto.LoginLogsDto;
import com.backend.admin.dto.PageDto;
import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login_logs")
public class LoginLogsController {
    @Autowired
    private LoginLogsService LoginLogsService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Response list(@RequestBody LoginLogsDto loginLogsDto){
        return LoginLogsService.getList(loginLogsDto);
    }

}
