package com.backend.admin.service.impl;

import com.backend.admin.constant.Constant;
import com.backend.admin.dto.PageDto;
import com.backend.admin.dto.PageDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.mapper.LoginLogsMapper;
import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.PageUtil;
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

    @Override
    public Response getList(String username, PageDto pageDto) {
        PageDto pageInfo = new PageUtil().pageVerify(pageDto);

        IPage<LoginLogs> logsIpage = new Page<>(pageInfo.getPage(),pageInfo.getLimit());
        QueryWrapper<LoginLogs> qw = new QueryWrapper<>();
        qw.orderByDesc("last_login_at");
        if (username != null) {
            qw.like("username",username);
        }
        IPage<LoginLogs> list = loginLogsMapper.selectPage(logsIpage,qw);

        return Response.success("获取成功",list);
    }
}
