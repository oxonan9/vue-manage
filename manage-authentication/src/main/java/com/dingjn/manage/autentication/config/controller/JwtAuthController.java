package com.dingjn.manage.autentication.config.controller;

import com.dingjn.manage.autentication.config.service.JwtAuthService;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc: 登录认证Controller
 */
@CrossOrigin
@RestController
public class JwtAuthController {

    @Resource
    private JwtAuthService jwtAuthService;

    @PostMapping("/authentication")
    public ServerResponse authentication(@RequestBody Map<String, String> map) {
        //获取用户名和密码
        String username = map.get("username");
        String password = map.get("password");

        //校验用户名和密码
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.error(CustomExceptionType.USER_INPUT_ERROR,
                    "用户名或密码不能为空");
        }
        //登录返回token
        return ServerResponse.success(jwtAuthService.login(username, password));
    }


    /**
     * 刷新JWT令牌
     */
    @RequestMapping(value = "/refreshtoken")
    public ServerResponse refresh(@RequestHeader("JWTHeaderName") String token) {
        return ServerResponse.success(jwtAuthService.refreshToken(token));
    }
}
