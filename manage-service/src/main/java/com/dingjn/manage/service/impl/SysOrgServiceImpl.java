package com.dingjn.manage.service.impl;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.util.DataTreeUtil;
import com.dingjn.manage.model.SysOrgNode;
import com.dingjn.manage.persistence.entity.SysOrg;
import com.dingjn.manage.persistence.mapper.SysOrgMapper;
import com.dingjn.manage.service.SysOrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Service
public class SysOrgServiceImpl implements SysOrgService {
    @Resource
    private SysOrgMapper sysOrgMapper;

    public List<SysOrgNode> getOrgTreeById(Integer rootOrgId,
                                           String orgNameLike,
                                           Boolean orgStatus) {
        if (rootOrgId != null) {
            List<SysOrg> sysOrgs
                    = sysOrgMapper.selectOrgTree(rootOrgId, orgNameLike, orgStatus);

            List<SysOrgNode> sysOrgNodes = sysOrgs.stream().map(item -> {
                SysOrgNode bean = new SysOrgNode();
                BeanUtils.copyProperties(item, bean);
                return bean;
            }).collect(Collectors.toList());

            if (StringUtils.isNotEmpty(orgNameLike) || orgStatus != null) {
                return sysOrgNodes;//根据条件查询，会破坏树形结构，所以返回平面列表
            } else {//否则返回树型结构列表
                return DataTreeUtil.buildTree(sysOrgNodes, rootOrgId);
            }

        } else {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                    "查询参数用户名组织id不能为空");
        }
    }
}
