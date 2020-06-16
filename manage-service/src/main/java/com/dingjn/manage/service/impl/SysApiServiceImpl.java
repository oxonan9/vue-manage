package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.util.DataTreeUtil;
import com.dingjn.manage.model.node.SysApiNode;
import com.dingjn.manage.persistence.entity.SysApi;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.persistence.entity.SysRoleApi;
import com.dingjn.manage.persistence.entity.SysRoleMenu;
import com.dingjn.manage.persistence.mapper.SysApiMapper;
import com.dingjn.manage.persistence.mapper.SysRoleApiMapper;
import com.dingjn.manage.service.SysApiService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: dingjn
 * @Desc: 接口管理Service实现类
 */
@Service
public class SysApiServiceImpl implements SysApiService {

    @Resource
    SysApiMapper sysApiMapper;

    @Resource
    SysRoleApiMapper sysRoleApiMapper;

    @Override
    public List<SysApiNode> getApiTree(String apiNameLike, Boolean orgStatus) {
        //1.找出根节点
        QueryWrapper<SysApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", 1);
        SysApi sysMenu = sysApiMapper.selectOne(queryWrapper);

        List<SysApi> sysMenuList = sysApiMapper.selectApiTree(sysMenu.getApiPid(), apiNameLike, null);
        List<SysApiNode> sysMenuNodeList = sysMenuList.stream().map(menu -> {
            SysApiNode sysMenuNode = new SysApiNode();
            BeanUtils.copyProperties(menu, sysMenuNode);
            return sysMenuNode;
        }).collect(Collectors.toList());

        if (!StringUtils.isBlank(apiNameLike)) {
            return sysMenuNodeList;
        } else {
            return DataTreeUtil.buildTree(sysMenuNodeList, sysMenu.getId());
        }
    }

    @Transactional
    public void saveApi(SysApi sysApi) {
        //判断是添加还是修改
        if (sysApi.getId() == null) {
            sysApi.setStatus(false);//默认不禁用
            sysApi.setLeaf(true);//默认是叶子节点
            setMenuPidsAndLevel(sysApi);
            //添加当前节点
            sysApiMapper.insert(sysApi);
            setMenuPidsAndLevel(sysApi);
        } else {
            sysApiMapper.updateById(sysApi);
        }
    }

    private void setMenuPidsAndLevel(SysApi child) {
        SysApi sysApi = sysApiMapper.selectById(child.getApiPid());
        //设置menuPids
        child.setApiPids(sysApi.getApiPids() + ",[" + child.getApiPid() + "]");
        //设置Level
        child.setLevel(sysApi.getLevel() + 1);
    }

    @Override
    @Transactional
    public void delApi(Integer id, Integer apiPid) {
        //查询当前节点是否有子节点
        QueryWrapper<SysApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("api_pids", "[" + id + "]");
        List<SysApi> sysMenuList = sysApiMapper.selectList(queryWrapper);
        if (sysMenuList.size() > 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "不能删除有下级菜单的菜单!");
        }
        setParentLeaf(apiPid);
        sysApiMapper.deleteById(id);
    }

    @Override
    public List<Integer> getDefauleExpandedKeys() {
        //默认展开
        QueryWrapper<SysApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", 2);
        List<SysApi> sysMenuList = sysApiMapper.selectList(queryWrapper);
        return sysMenuList.stream().map(SysApi -> SysApi.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getDefaultCheckedKeys(Integer roleId) {
        QueryWrapper<SysRoleApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<SysRoleApi> sysRoleApiList = sysRoleApiMapper.selectList(queryWrapper);
        return sysRoleApiList.stream().map(SysRoleApi::getApiId).collect(Collectors.toList());
    }

    //设置它的父节点为叶子节点
    private void setParentLeaf(Integer menuPid) {
        //查询父节点有几个节点
        QueryWrapper<SysApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("api_pids", "[" + menuPid + "]");
        List<SysApi> sysMenuList = sysApiMapper.selectList(queryWrapper);
        //如果只有它一下才设置
        if (sysMenuList.size() == 1) {
            SysApi parent = new SysApi();
            parent.setId(menuPid);
            parent.setLeaf(true);
            sysApiMapper.updateById(parent);
        }
    }

    @Override
    @Transactional
    public void saveApiPerm(Integer roleId, List<Integer> menuIds) {
        //在添加之前需要先讲之前的权限删掉
        QueryWrapper<SysRoleApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        sysRoleApiMapper.delete(queryWrapper);
        //添加
        sysRoleApiMapper.saveApiPerm(roleId, menuIds);
    }
}
