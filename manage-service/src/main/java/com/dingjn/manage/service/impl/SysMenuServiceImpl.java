package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.util.DataTreeUtil;
import com.dingjn.manage.model.node.SysMenuNode;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.persistence.entity.SysRoleMenu;
import com.dingjn.manage.persistence.mapper.SysMenuMapper;
import com.dingjn.manage.persistence.mapper.SysRoleMenuMapper;
import com.dingjn.manage.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    SysMenuMapper sysMenuMapper;

    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenuNode> getMenuTree(String orgNameLike, Boolean orgStatus) {
        //1.找出根节点
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", 1);
        SysMenu sysMenu = sysMenuMapper.selectOne(queryWrapper);


        List<SysMenu> sysMenuList = sysMenuMapper.selectMenuTree(sysMenu.getMenuPid(), orgNameLike, null);

        List<SysMenuNode> sysMenuNodeList = sysMenuList.stream().map(menu -> {
            SysMenuNode sysMenuNode = new SysMenuNode();
            BeanUtils.copyProperties(menu, sysMenuNode);
            return sysMenuNode;
        }).collect(Collectors.toList());

        if (!StringUtils.isBlank(orgNameLike)) {
            return sysMenuNodeList;
        } else {
            return DataTreeUtil.buildTree(sysMenuNodeList, sysMenu.getId());
        }
    }

    @Override
    @Transactional
    public void saveMenu(SysMenu sysMenu) {
        //判断是添加还是修改
        if (sysMenu.getId() == null) {
            sysMenu.setStatus(false);//默认不禁用
            sysMenu.setLeaf(true);//默认是叶子节点
            setMenuPidsAndLevel(sysMenu);
            //添加当前节点
            sysMenuMapper.insert(sysMenu);
            setMenuPidsAndLevel(sysMenu);
        } else {
            sysMenuMapper.updateById(sysMenu);
        }
    }

    private void setMenuPidsAndLevel(SysMenu child) {
        SysMenu sysMenu = sysMenuMapper.selectById(child.getMenuPid());
        //设置menuPids
        child.setMenuPids(sysMenu.getMenuPids() + ",[" + child.getMenuPid() + "]");
        //设置Level
        child.setLevel(sysMenu.getLevel() + 1);
    }

    @Override
    @Transactional
    public void delMenu(Integer id, Integer menuPid) {
        //查询当前节点是否有子节点
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("menu_pids", "[" + id + "]");
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        if (sysMenuList.size() > 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "不能删除有下级菜单的菜单!");
        }
        setParentLeaf(menuPid);
        sysMenuMapper.deleteById(id);
    }

    //设置它的父节点为叶子节点
    private void setParentLeaf(Integer menuPid) {
        //查询父节点有几个节点
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("menu_pids", "[" + menuPid + "]");
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        //如果只有它一下才设置
        if (sysMenuList.size() == 1) {
            SysMenu parent = new SysMenu();
            parent.setId(menuPid);
            parent.setLeaf(true);
            sysMenuMapper.updateById(parent);
        }
    }

    @Override
    public List<Integer> getDefauleExpandedKeys() {
        //默认展开
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", 2);
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        List<Integer> expandIdList = sysMenuList.stream().map(sysMenu -> sysMenu.getId()).collect(Collectors.toList());
        return expandIdList;
    }

    @Override
    public List<Integer> getDefaultCheckedKeys(Integer roleId) {
        return sysRoleMenuMapper.getCheckKeys(roleId);
    }

    @Override
    @Transactional
    public void saveMenuPerm(Integer roleId, List<Integer> menuIds) {
        //在添加之前需要先讲之前的权限删掉
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        sysRoleMenuMapper.delete(queryWrapper);
        //添加
        sysRoleMenuMapper.saveMenuPerm(roleId, menuIds);
    }


    public List<SysMenuNode> getMenuTreeByUsername(String username) {
        //根据用户名查询该用户可以访问的菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuByUsername(username);

        if (sysMenus.size() > 0) {
            Integer rootMenuId = sysMenus.get(0).getId(); //第一条记录为根节点
            //将List<SysMenu>转成List<SysMenuNode>
            List<SysMenuNode> sysMenuNodes =
                    sysMenus.stream().map(item -> {
                        SysMenuNode bean = new SysMenuNode();
                        BeanUtils.copyProperties(item, bean);
                        return bean;
                    }).collect(Collectors.toList());
            //构建树形结构节点列表
            return DataTreeUtil.buildTreeWithoutRoot(sysMenuNodes, rootMenuId);
        }
        return new ArrayList<>();
    }

}
