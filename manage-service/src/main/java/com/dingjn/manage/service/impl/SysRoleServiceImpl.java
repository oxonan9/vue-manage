package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingjn.manage.model.vo.SysRoleVO;
import com.dingjn.manage.persistence.entity.SysRole;
import com.dingjn.manage.persistence.mapper.SysRoleMapper;
import com.dingjn.manage.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: dingjn
 * @Desc: 权限管理Service实现类
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getRoles(String roleLike) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role_name", roleLike).
                or().like("role_code", roleLike).
                or().like("role_desc", roleLike);
        queryWrapper.orderByAsc("sort");
        return sysRoleMapper.selectList(queryWrapper);
    }

    public void saveRole(SysRoleVO sysRoleVO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleVO, sysRole);
        if (sysRole.getId() == null) {
            sysRoleMapper.insert(sysRole);
        } else {
            sysRoleMapper.updateById(sysRole);
        }
    }

    @Override
    public void delRole(Integer roleId) {
        sysRoleMapper.deleteById(roleId);
    }
}
