package com.dingjn.manage.web.config;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Auther: dingjn
 * @Desc: 全局异常处理
 */
@ControllerAdvice
public class GlobalExcepitonHandler {

    @ExceptionHandler(CustomException.class)
    public ServerResponse handle(CustomException e) {
        return ServerResponse.error(e.getCode(), e.getMessage());
    }
}
