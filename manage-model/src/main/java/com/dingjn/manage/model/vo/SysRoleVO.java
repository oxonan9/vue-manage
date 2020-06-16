package com.dingjn.manage.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleVO {
    private Integer id;
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @NotBlank(message = "角色描述不能为空")
    private String roleDesc;
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;
    private Integer sort;
}
