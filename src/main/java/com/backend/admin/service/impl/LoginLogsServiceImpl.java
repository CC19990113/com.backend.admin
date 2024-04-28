package com.backend.admin.service.impl;

import com.backend.admin.constant.Constant;
import com.backend.admin.dto.LoginLogsPageDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.mapper.LoginLogsMapper;
import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginLogsServiceImpl extends ServiceImpl<LoginLogsMapper, LoginLogs> implements LoginLogsService {

    @Resource
    private LoginLogsMapper loginLogsMapper;

    int page = Constant.PAGE;
    int limit = Constant.LIMIT;
    @Override
    public Response getList(LoginLogsPageDto loginLogsPageDto) {
        LoginLogsPageDto pageInfo = pageVerify(loginLogsPageDto);

        IPage<LoginLogs> logsIpage = new Page<>(pageInfo.getPage(),pageInfo.getLimit());
        QueryWrapper<LoginLogs> qw = new QueryWrapper<>();
        qw.orderByDesc("last_login_at");
        if (pageInfo.getUsername() != null) {
            qw.like("username",pageInfo.getUsername());
        }
        IPage<LoginLogs> logs = loginLogsMapper.selectPage(logsIpage,qw);

        return Response.success("获取成功",logs);
    }

    private LoginLogsPageDto pageVerify(LoginLogsPageDto loginLogsPageDto) {
        if(loginLogsPageDto.getPage() != null && loginLogsPageDto.getPage() > 0) {
            this.page = loginLogsPageDto.getPage();
        }
        if(loginLogsPageDto.getLimit() != null && loginLogsPageDto.getLimit() > 0) {
            this.limit = loginLogsPageDto.getLimit();
        }
        return new LoginLogsPageDto(this.page,this.limit,loginLogsPageDto.getUsername());
    }
}
