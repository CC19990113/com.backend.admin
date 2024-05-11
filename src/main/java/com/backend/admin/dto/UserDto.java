package com.backend.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDto extends PageDto{
    private Integer id;
    private String username;
    private Integer status;
}
