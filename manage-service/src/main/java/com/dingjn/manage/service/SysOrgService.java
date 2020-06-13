package com.dingjn.manage.service;

import com.dingjn.manage.model.SysOrgNode;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface SysOrgService {

    List<SysOrgNode> getOrgTreeById(Integer rootOrgId,
                                    String orgNameLike,
                                    Boolean orgStatus);
}
