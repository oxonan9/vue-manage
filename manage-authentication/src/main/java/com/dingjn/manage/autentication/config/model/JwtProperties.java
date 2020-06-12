package com.dingjn.manage.autentication.config.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @Auther: dingjn
 * @Desc: jwt的相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "manage.jwt")
public class JwtProperties {

    //是否开启JWT，即注入相关的类对象
    private Boolean enabled;
    //JWT密钥
    private String secret;
    //JWT有效时间
    private Long expiration;
    //前端向后端传递JWT时使用HTTP的header名称
    private String header;
    //允许哪些域对本服务的跨域请求
    private List<String> corsAllowedOrigins;
    //允许哪些HTTP方法跨域
    private List<String> corsAllowedMethods;
    //用户获取JWT令牌发送的用户名参数名称
    private String userParamName = "username";
    //用户获取JWT令牌发送的密码参数名称
    private String pwdParamName = "password";
    //是否关闭csrf跨站攻击防御功能
    private Boolean csrfDisabled = true;
    //是否使用默认的JWTAuthController
    private Boolean useDefaultController = true;
    //开发过程临时开放的URI
    private List<String> devOpeningURI;
    //权限全面开放的接口，不需要JWT令牌就可以访问
    private List<String> permitAllURI;

}
