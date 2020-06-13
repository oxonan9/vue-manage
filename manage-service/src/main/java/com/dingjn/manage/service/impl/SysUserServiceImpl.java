package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.persistence.mapper.SysUserMapper;
import com.dingjn.manage.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: dingjn
 * @Desc:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return sysUserMapper.selectOne(queryWrapper);
    }
}
