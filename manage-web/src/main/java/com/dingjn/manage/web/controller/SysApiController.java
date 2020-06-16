package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.model.vo.PermVO;
import com.dingjn.manage.persistence.entity.SysApi;
import com.dingjn.manage.service.SysApiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: dingjn
 * @Desc:  接口管理Api
 */
@RestController
@RequestMapping("/sysapi")
public class SysApiController {

    @Resource
    private SysApiService sysApiService;

    @PostMapping(value = "/tree")
    public ServerResponse tree(@RequestParam("apiNameLike") String apiNameLike) {
        return ServerResponse.success(sysApiService.getApiTree(apiNameLike, null));
    }


    @PostMapping(value = "/add")
    public ServerResponse saveOrg(@Valid @RequestBody SysApi sysApi) {
        sysApiService.saveApi(sysApi);
        return ServerResponse.success("保存接口信息成功！");
    }


    @PostMapping(value = "/delete")
    public ServerResponse delOrg(@RequestParam("id") Integer id,
                                 @RequestParam("apiPid") Integer apiPid) {
        if (id == null || apiPid == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "参数id和apiPid不能为空");
        }
        sysApiService.delApi(id, apiPid);
        return ServerResponse.success("删除接口信息成功！");
    }

    @PostMapping(value = "/checkedtree")
    public ServerResponse checkTree(@RequestParam("roleId") Integer roleId) {
        Map<String, Object> resultMap = new HashMap<>();
        // 1.获取全部的菜单树状数据
        resultMap.put("treeData", sysApiService.getApiTree(null, null));
        // 2.获取默认展开的数据 id
        resultMap.put("expandedKeys", sysApiService.getDefauleExpandedKeys());
        // 3.获取默认勾选的数据，也就是当前角色具有的菜单数据
        resultMap.put("checkedKeys", sysApiService.getDefaultCheckedKeys(roleId));
        return ServerResponse.success(resultMap);
    }

    /**
     * 保存菜单权限.
     */
    @PostMapping(value = "/savekeys")
    public ServerResponse<String> saveMenuPerm(@RequestBody PermVO permVO) {
        sysApiService.saveApiPerm(permVO.getRoleId(), permVO.getCheckKeys());
        return ServerResponse.success("保存菜单权限成功!");
    }
}
