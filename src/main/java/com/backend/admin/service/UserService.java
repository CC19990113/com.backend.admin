package com.backend.admin.service;

import com.backend.admin.dto.LoginDto;
import com.backend.admin.entity.User;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;


public interface UserService extends IService<User> {
    Response login(LoginDto loginDto, HttpServletRequest request);
}
