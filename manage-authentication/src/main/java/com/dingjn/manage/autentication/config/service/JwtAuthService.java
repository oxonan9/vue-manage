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
 * @Desc: jwt认证Service
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
     * @return 返回token，以后请求携带token进行验证.
     */
    public String login(String username, String password) {
        try {
            //1.构建Token凭证
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username, password);
            //2.对Token进行认证，返回认证主体
            Authentication authentication = authenticationManager.authenticate(token);
            //3.将主体加载到上下文中，下次会从上下文获取认证状态，避免重复认证
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
             throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或密码输入错误");
        }
        //4.返回用户信息
        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
        //5.生成token
        return jwtTokenUtil.generateToken(userDetails, null);
    }


    /**
     * 刷新token.
     * 成功返回新的token,失败返回null.
     * 如果返回的是null，说明前端传过来的token不存在了，前端可以根据这个接口进行做访问是跳转登录页面还是其他.
     */
    public String refreshToken(String oldToken){
        if(!jwtTokenUtil.isTokenExpired(oldToken)){
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
