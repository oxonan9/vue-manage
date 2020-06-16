package com.dingjn.manage.autentication.config.service;

import com.dingjn.manage.autentication.config.model.JwtProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Auther: dingjn
 * @Desc: 全局进行权限控制，判断用户访问某个url有没有权限
 */
@Component("rabcService")
public class MyRBACService {

    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        //1。获取当前用户信息
        Object principal = authentication.getPrincipal();

        //2.判断当前用户属不属于UserDetails，也就是他有没有认证通过
        if (principal instanceof UserDetails) {
            //3.获取请求的Url
            List<GrantedAuthority> authorityList =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());
            UserDetails userDetails = ((UserDetails) principal);

            //4.userDetails.getAuthorities()获取用户的权限列表，判断是否包含请求的url
            return userDetails.getAuthorities().contains(authorityList.get(0));
        }

        return false;
    }


}
