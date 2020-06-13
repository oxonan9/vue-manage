package com.dingjn.manage.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingjn.manage.persistence.entity.SysUser;
import com.dingjn.manage.persistence.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public void test() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        sysUsers.forEach(System.out::println);
    }


    /**
     * 分页查询.
     */
    @Test
    public void fenye() {
        //参数1：当前页数   参数2：每页记录数
        IPage<SysUser> iPage = new Page<>(1, 10);
        IPage<SysUser> sysUserIPage = sysUserMapper.selectPage(iPage, null);
        System.out.println("总记录数:"+sysUserIPage.getTotal());
        System.out.println("总页数:"+sysUserIPage.getPages());
        System.out.println("当前页数:"+sysUserIPage.getCurrent());
        System.out.println("记录:"+sysUserIPage.getRecords());
        System.out.println("大小:"+sysUserIPage.getSize());
    }


}
