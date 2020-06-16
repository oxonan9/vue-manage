package com.dingjn.manage.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserBO {
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
