package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.util.DataTree;
import com.dingjn.manage.common.util.DataTreeUtil;
import com.dingjn.manage.model.SysMenuNode;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.persistence.mapper.SysMenuMapper;
import com.dingjn.manage.service.SysMenuService;
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
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    SysMenuMapper sysMenuMapper;

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
}
