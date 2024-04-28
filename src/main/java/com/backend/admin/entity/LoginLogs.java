package com.backend.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("account_logs")
public class LoginLogs {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 最后一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)//创建与修改时自动填充
    private Date lastLoginAt;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIpAt;

    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 备注
     */
    private String remake;
}
