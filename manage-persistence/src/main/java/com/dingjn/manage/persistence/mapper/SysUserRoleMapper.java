package com.dingjn.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingjn.manage.persistence.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 Mapper 接口
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void saveRoles(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

}
