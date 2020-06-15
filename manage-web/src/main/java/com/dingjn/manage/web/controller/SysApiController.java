package com.dingjn.manage.web.controller;

import com.dingjn.manage.common.exception.CustomException;
import com.dingjn.manage.common.exception.CustomExceptionType;
import com.dingjn.manage.common.response.ServerResponse;
import com.dingjn.manage.persistence.entity.SysApi;
import com.dingjn.manage.persistence.entity.SysMenu;
import com.dingjn.manage.service.SysApiService;
import com.dingjn.manage.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Auther: dingjn
 * @Desc:
 */
@CrossOrigin
@RestController
public class SysApiController {

    @Resource
    private SysApiService sysApiService;

    @PostMapping(value = "/getApiTree")
    public ServerResponse tree(@RequestParam("apiNameLike") String apiNameLike) {
        return ServerResponse.success(sysApiService.getApiTree(apiNameLike, null));
    }


    @PostMapping(value = "/saveApi")
    public ServerResponse saveOrg(@Valid @RequestBody SysApi sysApi) {
        sysApiService.saveApi(sysApi);
        return ServerResponse.success("保存接口信息成功！");
    }


    @PostMapping(value = "/delApi")
    public ServerResponse delOrg(@RequestParam("id") Integer id,
                                 @RequestParam("apiPid") Integer apiPid) {
        if (id == null || apiPid == null) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "参数id和apiPid不能为空");
        }
        sysApiService.delApi(id, apiPid);
        return ServerResponse.success("删除接口信息成功！");
    }
}
