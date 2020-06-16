package com.dingjn.manage.model.vo;

import com.dingjn.manage.model.node.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVO extends SysUser {
    String orgName;
}
