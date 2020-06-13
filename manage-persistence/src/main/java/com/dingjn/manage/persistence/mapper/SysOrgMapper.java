package com.dingjn.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingjn.manage.persistence.entity.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统组织结构表 Mapper 接口
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    List<SysOrg> selectOrgTree(@Param("rootOrgId") Integer rootOrgId,
                               @Param("orgNameLike") String orgNameLike,
                               @Param("orgStatus") Boolean orgStatus);
}
