package com.dingjn.manage.service;

import com.dingjn.manage.model.node.SysApiNode;
import com.dingjn.manage.persistence.entity.SysApi;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc: 接口管理Service
 */
public interface SysApiService {

    /**
     * 获取接口全部信息 树状.
     */
    List<SysApiNode> getApiTree(String apiNameLike,
                                Boolean orgStatus);

    /**
     * 新增/修改菜单.
     */
    void saveApi(SysApi sysApi);

    /**
     * 删除菜单.
     */
    void delApi(Integer id, Integer apiPid);


    /**
     * 获取默认展开的数据 返回id集合.
     */
    List<Integer> getDefauleExpandedKeys();

    /**
     * 获取当前角色默认具有的菜单 返回id集合.
     */
    List<Integer> getDefaultCheckedKeys(Integer roleId);


    /**
     * 保存角色的接口权限.
     */
    void saveApiPerm(Integer roleId,List<Integer> apiIds);

}
