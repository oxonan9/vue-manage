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
    //是否关闭csrf跨站攻击防御功能
    private Boolean csrfDisabled = true;
    //是否使用默认的JWTAuthController
    private Boolean useDefaultController = true;

}
