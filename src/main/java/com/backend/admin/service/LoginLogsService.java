package com.backend.admin.service;

import com.backend.admin.dto.LoginLogsDto;
import com.backend.admin.dto.PageDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LoginLogsService extends IService<LoginLogs> {

    Response getList(LoginLogsDto loginLogsDto);

}
