package com.dingjn.manage.autentication.config.service;

import com.dingjn.manage.autentication.config.mapper.MyUserDetailsServiceMapper;
import com.dingjn.manage.autentication.config.model.MyUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: dingjn
 * @Desc: 动态加载用户信息，用户登录后会先访问这个接口拿到用户信息，然后再去认证
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //1.加载基础用户信息
        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUserName(username);

        //2.加载用户角色列表
        List<String> roleCodes = myUserDetailsServiceMapper.findRoleByUserName(username);

        if (roleCodes!=null&&roleCodes.size() > 0) {
            //3.通过用户角色列表加载用户的资源权限列表
            List<String> authorties = myUserDetailsServiceMapper.findApiByRoleCodes(roleCodes);
            //4.角色是一个特殊的权限，SpringSecurity规定对于角色需要加上ROLE_前缀
            roleCodes = roleCodes.stream()
                    .map(rc -> "ROLE_" + rc)
                    .collect(Collectors.toList());

            authorties.addAll(roleCodes);

            //5.将用户权限列表赋给用户信息
            myUserDetails.setAuthorities(
                    AuthorityUtils.commaSeparatedStringToAuthorityList(
                            String.join(",", authorties)
                    )
            );
        }
        return myUserDetails;

    }


}
