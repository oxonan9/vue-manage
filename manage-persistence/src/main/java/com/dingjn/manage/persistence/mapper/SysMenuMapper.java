package com.dingjn.manage.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingjn.manage.persistence.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author dingjn
 * @since 2020-06-13
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuTree(@Param("rootMenuId") Integer rootMenuId ,
                                 @Param("menuNameLike") String menuNameLike,
                                 @Param("menuStatus") Boolean menuStatus);

}
