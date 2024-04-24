package com.backend.admin.utils;

import lombok.Data;

@Data
public class Response{
    private Integer code;
    private String msg;
    private Object data;
    /**
     * 成功相应方法
     * @param data 相应的数据
     */
    public static Response success(Object data)
    {
        Response response = new Response();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response;
    }
    public static Response error(Object data)
    {
        Response response = new Response();
        response.setCode(201);
        response.setMsg("error");
        response.setData(data);
        return response;
    }
}
