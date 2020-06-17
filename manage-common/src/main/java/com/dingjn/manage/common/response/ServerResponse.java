package com.dingjn.manage.common.response;

import com.dingjn.manage.common.exception.CustomExceptionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: dingjn
 * @Desc: 统一响应类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    /* 状态码 */
    private int status;
    /* 消息 */
    private String message;
    /* 实体 */
    private T data;

    public ServerResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ServerResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServerResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }


    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(), data);
    }

    public static <T> ServerResponse<T> success(String message, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ServerResponse<T> error(int status, String message) {
        return new ServerResponse<T>(status, message);
    }

    public static <T> ServerResponse<T> error(String message) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ServerResponse<T> error(CustomExceptionType type, String message) {
        return new ServerResponse<T>(type.getCode(), message);
    }

    /**
     * 是否成功 不返回给前端.
     */
    @JsonIgnore
    public boolean isSuccess() {
        return status == 1;
    }
}

