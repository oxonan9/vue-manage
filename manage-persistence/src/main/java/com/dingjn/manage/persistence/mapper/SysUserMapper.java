package com.dingjn.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingjn.manage.model.dto.SysUserDTO;
import com.dingjn.manage.model.vo.SysUserVO;
import com.dingjn.manage.persistence.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUserVO> getUsers(SysUserDTO sysUserBO);

    List<Integer> getCheckedRoleIds(Integer userId);

}
