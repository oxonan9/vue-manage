package com.dingjn.manage.autentication.config.config;


import com.dingjn.manage.autentication.config.model.JwtProperties;
import com.dingjn.manage.autentication.config.service.MyUserDetailService;
import com.dingjn.manage.autentication.config.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT令牌授权过滤器
 * 1.判断令牌的有效性
 * 2.根据令牌为该用户授权可以访问的资源
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtProperties jwtProperties;


    @Resource
    private JwtTokenUtil jwtTokenUtil;


    @Resource
    private MyUserDetailService myUserDetailService;

    private JwtAuthenticationTokenFilter() {
    }

    public JwtAuthenticationTokenFilter(JwtProperties jwtProperties,
                                        JwtTokenUtil jwtTokenUtil,
                                        MyUserDetailService myUserDetailService) {
        this.jwtProperties = jwtProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = request.getHeader(jwtProperties.getHeader());
        if (!StringUtils.isEmpty(jwtToken)) {
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    //给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}