package com.dingjn.manage.model.node.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc: 分配权限Vo类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermVO {
    //角色id
    private Integer roleId;
    //menu或api的ids
    private List<Integer> checkKeys;
}
