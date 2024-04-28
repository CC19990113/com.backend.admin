package com.backend.admin.config;

import com.backend.admin.utils.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Response handleException(Exception e){
        return Response.error(e.getMessage());
}
}
