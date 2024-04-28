package com.backend.admin.service.impl;

import com.backend.admin.entity.LoginLogs;
import com.backend.admin.mapper.LoginLogsMapper;
import com.backend.admin.service.LoginLogsService;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginLogsServiceImpl extends ServiceImpl<LoginLogsMapper, LoginLogs> implements LoginLogsService {

    @Override
    public Response getList() {
        return Response.success("获取成功",null);
    }
}
