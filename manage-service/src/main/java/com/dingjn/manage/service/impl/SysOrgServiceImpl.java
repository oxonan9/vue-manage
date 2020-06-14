package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.util.DataTreeUtil;
import com.dingjn.manage.model.SysOrgNode;
import com.dingjn.manage.persistence.entity.SysOrg;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.persistence.mapper.SysOrgMapper;
import com.dingjn.manage.service.SysOrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    //查询组织信息 返沪树状结构
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

    @Override
    @Transactional
    public void saveOrg(SysOrg sysOrg) {
        if(sysOrg.getId()==null){
            //1.添加节点
            setOrgPidsAndLevel(sysOrg);
            sysOrg.setStatus(false);//默认不禁用
            sysOrg.setLeaf(true);//新增的节点都是叶子节点
            sysOrgMapper.insert(sysOrg);
            //2.更新父节点为非叶子节点
            SysOrg parent = new SysOrg();
            parent.setId(sysOrg.getOrgPid());
            parent.setLeaf(false);
            sysOrgMapper.updateById(parent);
        }else{
            //修改节点
            sysOrgMapper.updateById(sysOrg);
        }

    }

    //为新增的节点设置org_pids和level
    private void setOrgPidsAndLevel(SysOrg child) {
        //1.查询出全部部门
        List<SysOrg> sysOrgList = sysOrgMapper.selectList(null);
        for (SysOrg sysOrg : sysOrgList) {
            //2.找出新增节点的直接父节点
            if (sysOrg.getId() == child.getOrgPid()) {
                //3.直接父节点的所有父节点id+直接父节点的id=新增节点的所有父id
                //因为数据库中的org_pids格式为 [1],[2],[3]
                child.setOrgPids(sysOrg.getOrgPids() + "[" + child.getOrgPid() + "]");
                //4.设置新增节点的level  为父节点的level+1
                child.setLevel(sysOrg.getLevel() + 1);
            }
        }

    }

    @Override
    @Transactional
    public void delOrg(Integer id, Integer orgPid) {
        //查询当前节点下的子节点，如果有子节点不能删除，否则会破坏掉树形结构，导致它的子节点没有根源
        QueryWrapper<SysOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("org_pids", "[" + id + "]");
        List<SysOrg> childSysOrgList = sysOrgMapper.selectList(queryWrapper);
        if (childSysOrgList.size() > 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "不能删除有下级组织的组织机构");
        }
        setParentLeaf(orgPid);
        sysOrgMapper.deleteById(id);

    }

    //如果当前节点的上级组织只有它一个节点，那么当前节点被删除以后，上级组织应该变为叶子节点
    private void setParentLeaf(Integer orgPid) {
        //找出当前父节点有几个子节点
        QueryWrapper<SysOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("org_pids", "[" + orgPid + "]");
        List<SysOrg> childSysOrgList = sysOrgMapper.selectList(queryWrapper);
        if (childSysOrgList.size() == 1) {
            //设置Leaf为true
            SysOrg parent = new SysOrg();
            parent.setId(orgPid);
            parent.setLeaf(true);
            sysOrgMapper.updateById(parent);
        }
    }
}
