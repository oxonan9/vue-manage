package com.dingjn.manage.web;

import java.time.LocalDateTime;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingjn.manage.model.node.SysApiNode;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.persistence.mapper.SysUserMapper;
import com.dingjn.manage.service.SysApiService;
import com.dingjn.manage.service.SysUserService;
import com.dingjn.manage.service.impl.SysApiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */

@SpringBootTest
public class UserMapperTest {

    @Resource
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test() {
//        List<SysApiNode> apiTree = sysApiService.getApiTree(null, null);
//        System.out.println(apiTree);
    }


    /**
     * 分页查询.
     */
    @Test
    public void fenye() {
        //参数1：当前页数   参数2：每页记录数
        IPage<SysUser> iPage = new Page<>(1, 10);
        IPage<SysUser> sysUserIPage = sysUserMapper.selectPage(iPage, null);
        System.out.println("总记录数:" + sysUserIPage.getTotal());
        System.out.println("总页数:" + sysUserIPage.getPages());
        System.out.println("当前页数:" + sysUserIPage.getCurrent());
        System.out.println("记录:" + sysUserIPage.getRecords());
        System.out.println("大小:" + sysUserIPage.getSize());
    }


    @Test
    public void save() {
        //参数1：当前页数   参数2：每页记录数
        for (int i = 0; i < 105; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUsername("匿名:"+i);
            sysUser.setPassword(passwordEncoder.encode("123456"));
            sysUser.setOrgId(3);
            sysUser.setEnabled(false);
            sysUser.setPhone("17376554257");
            sysUser.setEmail("code123@qq.com");
            sysUser.setCreateTime(LocalDateTime.now());
            sysUserService.saveUser(sysUser);
        }


    }


}
