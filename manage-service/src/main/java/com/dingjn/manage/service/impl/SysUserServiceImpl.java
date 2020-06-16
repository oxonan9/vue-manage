package com.dingjn.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.dingjn.manage.model.dto.SysUserDTO;
import com.dingjn.manage.model.dto.SysUserRoleDTO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysRole;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.persistence.entity.SysUserRole;
import com.dingjn.manage.persistence.mapper.SysRoleMapper;
import com.dingjn.manage.persistence.mapper.SysUserMapper;
import com.dingjn.manage.persistence.mapper.SysUserRoleMapper;
import com.dingjn.manage.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc: 用户管理Service实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 密码加密处理.
     */
    @Resource
    private PasswordEncoder passwordEncoder;



    @Override
    public SysUser getUserByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public PageInfo<SysUserVO> getUsers(SysUserDTO sysUserBO) {
        PageHelper.startPage(sysUserBO.getPageNum(), sysUserBO.getPageSize());
        List<SysUserVO> userVOList = sysUserMapper.getUsers(sysUserBO);
        return PageInfo.of(userVOList);
    }

    @Override
    public void saveUser(SysUser sysuser) {
        if (sysuser.getId() == null) {
            //TODO 初始密码优化为通用配置
            sysuser.setPassword(passwordEncoder.encode("123456"));
            sysuser.setCreateTime(LocalDateTime.now());  //创建时间
            sysuser.setEnabled(true); //新增用户激活
            sysUserMapper.insert(sysuser);
        } else {
            sysUserMapper.updateById(sysuser);
        }

    }

    @Override
    public Map<String, Object> getCheckedRoles(Integer userId) {
        HashMap<String, Object> resultMap = new HashMap<>();
        //1.全部的角色
        List<SysRole> roleDatas = sysRoleMapper.selectList(null);
        //2.当前用户具有的角色
        List<Integer> checkedRoleIds = sysUserMapper.getCheckedRoleIds(userId);
        resultMap.put("roleDatas", roleDatas);
        resultMap.put("checkedRoleIds", checkedRoleIds);
        return resultMap;
    }

    @Override
    public void deleteUser(Integer userId) {
        sysUserMapper.deleteById(userId);
    }

    @Override
    @Transactional
    public void saveRoles(SysUserRoleDTO sysUserRoleDTO) {
        //保存角色信息之前先删除之前的信息
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", sysUserRoleDTO.getUserId());
        sysUserRoleMapper.delete(queryWrapper);
        sysUserRoleMapper.saveRoles(sysUserRoleDTO.getUserId(), sysUserRoleDTO.getCheckedroleIds());
    }
}
