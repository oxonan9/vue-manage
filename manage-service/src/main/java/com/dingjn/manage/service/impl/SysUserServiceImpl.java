package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.model.bo.SysUserBO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.persistence.mapper.SysUserMapper;
import com.dingjn.manage.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<SysUserVO> getUsers(SysUserBO sysUserBO) {
        return sysUserMapper.getUsers(sysUserBO);

    }


    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(SysUser sysuser) {
        if (sysuser.getId() == null) {
            //TODO 初始密码可以优化为通用配置
                sysuser.setPassword(passwordEncoder.encode("123456"));
            sysuser.setCreateTime(LocalDateTime.now());  //创建时间
            sysuser.setEnabled(true); //新增用户激活
            sysUserMapper.insert(sysuser);
        } else {
            sysUserMapper.updateById(sysuser);
        }

    }

    @Override
    public void deleteUser(Integer userId) {
        sysUserMapper.deleteById(userId);
    }
}
