package com.backend.admin.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.backend.admin.dto.LoginDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.entity.User;
import com.backend.admin.mapper.LoginLogsMapper;
import com.backend.admin.mapper.UserMapper;
import com.backend.admin.service.UserService;
import com.backend.admin.utils.IpUtil;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginLogsMapper loginLogsMapper;

    @Override
    public Response login(LoginDto loginDto, HttpServletRequest request) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        if (username == null || password == null) {
            return Response.error("用户名或密码不能为空");
        }
        String ipAddress = IpUtil.getIpAddr(request);
        if (!userExists(username)) {
            return Response.error("用户不存在");
        }
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (u.getStatus() == 0) {
            return accountDisabled(username, ipAddress);
        }
        if (!isAccountActive(u)) {
            return accountBlocked(username, ipAddress);
        }
        if (!passwordMatches(u, password)) {
            return loginFailed(username, ipAddress, u);
        }
        return loginSuccess(username, ipAddress, u);
    }

    private boolean userExists(String username) {
        return userMapper.selectCount(new QueryWrapper<User>().eq("username", username)) > 0;
    }

    private Response accountDisabled(String username, String ipAddress) {
        LoginLogs ll = new LoginLogs();
        ll.setLastLoginIpAt(ipAddress);
        ll.setUsername(username);
        ll.setRemake("用户被禁用");
        loginLogsMapper.insert(ll);
        return Response.error("用户被禁用");
    }

    private boolean isAccountActive(User user) {
        return user.getLoginCount() > 0;
    }

    private Response accountBlocked(String username, String ipAddress) {
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        LoginLogs ll = new LoginLogs();
        ll.setLastLoginIpAt(ipAddress);
        ll.setUsername(username);
        ll.setRemake("账号被封禁");
        loginLogsMapper.insert(ll);
        u.setStatus(0);
        userMapper.updateById(u);
        return Response.error("账号已被封禁，请联系管理员解封");
    }

    private boolean passwordMatches(User user, String password) {
        String hashedPassword = DigestUtil.sha256Hex(password);
        return hashedPassword.equals(user.getPassword());
    }

    private Response loginFailed(String username, String ipAddress, User user) {
        int count = user.getLoginCount() - 1;
        user.setLoginCount(count);
        userMapper.updateById(user);

        LoginLogs ll = new LoginLogs();
        ll.setLastLoginIpAt(ipAddress);
        ll.setUsername(username);
        ll.setLoginCount(count);
        ll.setRemake("密码错误，剩余登录次数" + count);
        loginLogsMapper.insert(ll);

        return Response.error("密码错误，剩余登录次数" + count);
    }

    private Response loginSuccess(String username, String ipAddress, User user) {
        user.setLastLoginIpAt(ipAddress);
        user.setLoginCount(5);
        userMapper.updateById(user);

        LoginLogs ll = new LoginLogs();
        ll.setLastLoginIpAt(ipAddress);
        ll.setUsername(username);
        loginLogsMapper.insert(ll);

        HashMap<String, Object> token = new HashMap<>();
        token.put("token", "xxx");

        return Response.success("success", token);
    }


}
