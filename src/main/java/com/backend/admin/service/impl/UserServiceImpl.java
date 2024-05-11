package com.backend.admin.service.impl;

import com.backend.admin.dto.PageDto;
import com.backend.admin.dto.UserDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.entity.User;
import com.backend.admin.entity.UserInfo;
import com.backend.admin.mapper.LoginLogsMapper;
import com.backend.admin.mapper.UserMapper;
import com.backend.admin.service.UserService;
import com.backend.admin.utils.IpUtil;
import com.backend.admin.utils.PageUtil;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginLogsMapper loginLogsMapper;
    @Override
    public Response getList(UserDto userdto) {
        int page = userdto.getPage();
        int limit = userdto.getLimit();

        PageDto pageDto = new PageDto(page,limit);
        PageDto pageInfo = new PageUtil().pageVerify(pageDto);

        IPage<UserInfo> userPage = new Page<>(pageInfo.getPage(), pageInfo.getLimit());
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.orderByDesc("create_time");
        qw.like("username", userdto.getUsername());
        if(userdto.getStatus() != null){
            qw.eq("status", userdto.getStatus());
        }
        IPage<UserInfo> list = userMapper.selectPage(userPage, qw);
        return Response.success("查询成功", list);
    }

    @Override
    public Response updateStatus(UserDto userDto) {
        if(userDto.getId() == null) {
            throw new RuntimeException("id不能为空");
        }
        UserInfo userInfo = userMapper.selectById(userDto.getId());
        if(userInfo == null){
            throw new RuntimeException("用户不存在");
        }
        if(Objects.equals(userDto.getStatus(), userInfo.getStatus()) | userDto.getStatus() == null){
            throw new RuntimeException("状态不能为空");
        }
        userInfo.setStatus(userDto.getStatus());
        userMapper.updateById(userInfo);

        return Response.success("修改成功", null);
    }

    @Override
    public Response delete(UserDto userDto) {
        if(userDto.getId() == null) {
            throw new RuntimeException("id不能为空");
        }
        UserInfo userInfo = userMapper.selectById(userDto.getId());
        if(userInfo == null){
            throw new RuntimeException("用户不存在");
        }
        userMapper.deleteById(userDto.getId());

        return Response.success("删除成功", null);
    }

}
