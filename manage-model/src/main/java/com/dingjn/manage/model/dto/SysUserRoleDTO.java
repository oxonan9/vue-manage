package com.dingjn.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleDTO {
    private Integer userId;
    private List<Integer> checkedroleIds;
}
