package com.backend.admin.service.impl;

import com.backend.admin.dto.LoginDto;
import com.backend.admin.entity.User;
import com.backend.admin.mapper.UserMapper;
import com.backend.admin.service.UserService;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Response login(LoginDto loginDto){
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        if(username == null || password == null) {
            return Response.error("用户名或密码不能为空");
        }
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        User u = userMapper.selectOne(qw);
        if(u == null) {
            return Response.error("用户不存在");
        }
        if(u.getStatus() == 0) {
            return Response.error("用户被禁用");
        }
        if(u.getLoginCount() <= 0) {
            u.setStatus(0);
            userMapper.updateById(u);
            return Response.error("账号已被封禁，请联系管理员解封");
        }
        if(!password.equals(u.getPassword())) {
            int count = u.getLoginCount();
            u.setLoginCount(count - 1);
            userMapper.updateById(u);
            return Response.error("密码错误，剩余次数" + (count - 1));
        }
        u.setLastLoginIpAt("2.2.2.3");
        userMapper.updateById(u);
        return Response.success("登陆成功");
    };
}
