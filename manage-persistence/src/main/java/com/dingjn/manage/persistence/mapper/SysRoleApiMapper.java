package com.dingjn.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingjn.manage.persistence.entity.SysRoleApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色接口权限关系表 Mapper 接口
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public interface SysRoleApiMapper extends BaseMapper<SysRoleApi> {
    int saveApiPerm(@Param("roleId") Integer roleId, @Param("apiIds") List<Integer> apiIds);

}
