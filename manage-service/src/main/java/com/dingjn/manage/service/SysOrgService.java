package com.dingjn.manage.service;

import com.dingjn.manage.model.SysOrgNode;
import com.dingjn.manage.persistence.entity.SysOrg;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysOrgService {

    List<SysOrgNode> getOrgTreeById(Integer rootOrgId,
                                    String orgNameLike,
                                    Boolean orgStatus);

    /**
     * 新增/修改组织.
     */
    void saveOrg(SysOrg sysOrg);

    /**
     * 删除组织.
     */
    void delOrg(Integer id, Integer orgPid);
}
