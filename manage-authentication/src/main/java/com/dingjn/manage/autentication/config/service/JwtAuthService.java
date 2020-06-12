package com.dingjn.manage.autentication.config.service;

import com.dingjn.manage.autentication.config.util.JwtTokenUtil;
import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Service
public class JwtAuthService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private MyUserDetailService myUserDetailService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;


    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回token，以后请求携带token进行验证
     */
    public String login(String username, String password) {
        try {
            //构建Token凭证
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username, password);
            //对Token进行认证，返回认证主体
            Authentication authentication = authenticationManager.authenticate(token);
            //将主体加载到上下文中，下次会从上下文获取认证状态，避免重复认证
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或密码输入错误");
        }
        //返回用户信息
        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        //生成token
        return jwtTokenUtil.generateToken(userDetails, null);
    }
}
