package com.dingjn.manage.service;

import com.dingjn.manage.model.SysApiNode;
import com.dingjn.manage.persistence.entity.SysApi;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysApiService {
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
}
