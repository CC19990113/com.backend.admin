package com.backend.admin.service;

import com.backend.admin.dto.LoginLogsPageDto;
import com.backend.admin.entity.LoginLogs;
import com.backend.admin.utils.Response;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LoginLogsService extends IService<LoginLogs> {

    Response getList(LoginLogsPageDto loginLogsPageDto);

}
