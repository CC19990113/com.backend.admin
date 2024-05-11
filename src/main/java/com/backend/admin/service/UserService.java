package com.backend.admin.service;

import com.backend.admin.dto.PageDto;
import com.backend.admin.dto.UserDto;
import com.backend.admin.entity.User;
import com.backend.admin.entity.UserInfo;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;


public interface UserService extends IService<UserInfo> {
    Response getList(UserDto userdto);

    Response updateStatus(UserDto userDto);

    Response delete(UserDto userDto);
}
