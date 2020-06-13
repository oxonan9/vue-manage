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
    private String msg;
    /* 实体 */
    private T data;

    public ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }


    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(), data);
    }

    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> error(int status, String msg) {
        return new ServerResponse<T>(status, msg);
    }

    public static <T> ServerResponse<T> error(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> error(CustomExceptionType type, String msg) {
        return new ServerResponse<T>(type.getCode(), msg);
    }

    /**
     * 是否成功 不返回给前端.
     */
    @JsonIgnore
    public boolean isSuccess() {
        return status == 1;
    }
}

