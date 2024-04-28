package com.backend.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginLogsPageDto {
    private Integer page;
    private Integer limit;
    /**
     * 搜索内容 可以是ID 用户名等
     */
    private String username;
}
