package com.dingjn.manage.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Getter
@AllArgsConstructor
enum ResponseCode {

    SUCCESS(1, "success"),
    ERROR(0, "error");

    private int code;
    private String msg;
}
