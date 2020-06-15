package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.persistence.entity.SysApi;
import com.dingjn.manage.service.SysApiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc:
 */
@CrossOrigin
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
