package com.backend.admin.service;

import com.backend.admin.dto.LoginDto;
import com.backend.admin.entity.User;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserService extends IService<User> {
    Response login(LoginDto loginDto);
}
