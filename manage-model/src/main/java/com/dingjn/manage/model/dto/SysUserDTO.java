package com.dingjn.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: dingjn
 * @Desc:  查询用户的传输类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO {
    Integer orgId;
    String username;
    String phone;
    String email;
    Boolean enabled;
    Date createStartTime;
    Date createEndTime;
    Integer pageNum;
    Integer pageSize;
}
