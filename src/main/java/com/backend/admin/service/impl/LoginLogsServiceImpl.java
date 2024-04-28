package com.backend.admin.service.impl;

import com.backend.admin.constant.Constant;
import com.backend.admin.dto.PageDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.entity.User;
import com.backend.admin.mapper.LoginLogsMapper;
import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class LoginLogsServiceImpl extends ServiceImpl<LoginLogsMapper, LoginLogs> implements LoginLogsService {

    @Resource
    private LoginLogsMapper loginLogsMapper;
    @Override
    public Response getList(PageDto pageDto) {
        int page = Constant.PAGE;
        int limit = Constant.LIMIT;
        if(pageDto.getPage() != null && pageDto.getPage() > 0) {
            page = pageDto.getPage();
        }
        if(pageDto.getLimit() != null && pageDto.getLimit() > 0) {
            limit = pageDto.getLimit();
        }

        IPage<LoginLogs> logsIpage = new Page<>(page,limit);
        QueryWrapper<LoginLogs> qw = new QueryWrapper<>();
        qw.orderByDesc("last_login_at");
        IPage<LoginLogs> logs = loginLogsMapper.selectPage(logsIpage,qw);

        return Response.success("获取成功",logs);
    }
}
