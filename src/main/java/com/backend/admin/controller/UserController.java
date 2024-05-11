package com.backend.admin.controller;

import com.backend.admin.dto.PageDto;
import com.backend.admin.dto.UserDto;
import com.backend.admin.service.UserService;
import com.backend.admin.utils.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @RequestMapping(value ="/list", method = RequestMethod.POST)
    public Response list(@RequestBody UserDto userdto)
    {
        return userService.getList(userdto);
    }

    @RequestMapping(value = "/update_status", method = RequestMethod.POST)
    public Response updateStatus(@RequestBody UserDto userDto) {
        return userService.updateStatus(userDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody UserDto userDto) {
        return userService.delete(userDto);
    }
}
