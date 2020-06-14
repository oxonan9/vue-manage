package com.dingjn.manage.web.config;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: dingjn
 * @Desc: 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExcepitonHandler {

    @ExceptionHandler(CustomException.class)
    public ServerResponse handle(CustomException e) {

        return ServerResponse.error(e.getCode(), e.getMessage());
    }

    /**
     * 参数校验异常 BindingResult.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ServerResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验异常，提示信息:{}，具体错误信息:{}", e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), e.getMessage());
        return ServerResponse.error(CustomExceptionType.USER_INPUT_ERROR.getCode(), e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
}
