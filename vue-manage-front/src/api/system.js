import { jwtServerInstance } from './index'
import { getTokenUser } from "@/lib/utils.js";


//JWT登录认证接口
export const login = (userName, passWord) => {
    return jwtServerInstance.request({
        url: '/authentication',
        method: 'post',
        data: {
            username: userName,
            password: passWord
        }
    })
}

/*
    组织管理相关Api
*/
export const orgTree = (orgName) => {
    return jwtServerInstance.request({
        url: '/sysorg/tree',
        method: 'post',
        params: {
            username: getTokenUser(),
            orgNameLike: orgName
        }
    })
}
export const orgSave = (orgForm) => {
    return jwtServerInstance.request({
        url: '/sysorg/add',
        method: 'post',
        data: orgForm
    })
}

export const orgDel = (id, orgPid) => {
    return jwtServerInstance.request({
        url: '/sysorg/delete',
        method: 'post',
        params: {
            id: id,
            orgPid: orgPid
        }
    })
}


export const menuTree = (menuNameLike) => {
    return jwtServerInstance.request({
        url: '/sysmenu/tree',
        method: 'post',
        params: {
            menuNameLike: menuNameLike
        }
    })
}

export const menuSave = (menuForm) => {
    return jwtServerInstance.request({
        url: '/sysmenu/add',
        method: 'post',
        data: menuForm
    })
}

export const menuDel = (id, menuPid) => {
    return jwtServerInstance.request({
        url: '/sysmenu/delete',
        method: 'post',
        params: {
            id: id,
            menuPid: menuPid
        }
    })
}


/*
    接口管理相关Api
*/
export const apiTree = (apiNameLike) => {
    return jwtServerInstance.request({
        url: '/sysapi/tree',
        method: 'post',
        params: {
            apiNameLike: apiNameLike
        }
    })
}

export const apiSave = (apiSaveForm) => {
    return jwtServerInstance.request({
        url: '/sysapi/add',
        method: 'post',
        data: apiSaveForm
    })
}

export const apiDel = (id, apiPid) => {
    return jwtServerInstance.request({
        url: '/sysapi/delete',
        method: 'post',
        params: {
            id: id,
            apiPid: apiPid
        }
    })
}


/*
    角色相关Api
*/
export const getRoles = (roleLike) => {
    return jwtServerInstance.request({
        url: '/sysrole/query',
        method: 'get',
        params: {
            roleLike: roleLike
        }
    })
}

export const saveRole = (roleForm) => {
    return jwtServerInstance.request({
        url: '/sysrole/add',
        method: 'post',
        data: roleForm
    })
}

export const delRole = (roleId) => {
    return jwtServerInstance.request({
        url: '/sysrole/delete',
        method: 'post',
        params: {
            id: roleId
        }
    })
}


/*
    分配权限相关Api
*/
//菜单
export const checkMenuTree = (roleId) => {
    return jwtServerInstance.request({
        url: '/sysmenu/checkedtree',
        method: 'post',
        params: {
            roleId: roleId
        }
    })
}

export const saveMenuPerm = (roleId, menuIds) => {
    return jwtServerInstance.request({
        url: '/sysmenu/savekeys',
        method: 'post',
        data: {
            roleId: roleId,
            checkKeys: menuIds
        }
    })
}

//接口
export const checkApiTree = (roleId) => {
    return jwtServerInstance.request({
        url: '/sysapi/checkedtree',
        method: 'post',
        params: {
            roleId: roleId
        }
    })
}
//保存
export const saveApiPerm = (roleId, apiIds) => {
    return jwtServerInstance.request({
        url: '/sysapi/savekeys',
        method: 'post',
        data: {
            roleId: roleId,
            checkKeys: apiIds
        }
    })
}


/*
    用户管理相关Api
*/
export const getUsers = (queryform, pagination) => {
    return jwtServerInstance.request({
        url: '/sysuser/query',
        method: 'get',
        params: {
            orgId: queryform.orgId,
            username: queryform.username,
            phone: queryform.phone,
            email: queryform.email,
            enabled: queryform.enabled,
            createStartTime: queryform.timeRange[0],
            createEndTime: queryform.timeRange[1],
            pageNum: pagination.pageNum,
            pageSize: pagination.pageSize
        }
    })
}

export const saveUser = (userForm) => {
    return jwtServerInstance.request({
        url: '/sysuser/add',
        method: 'post',
        data: userForm
    })
}


export const delUser = (userId) => {
    return jwtServerInstance.request({
        url: '/sysuser/delete',
        method: 'post',
        params: {
            userId: userId,
        }
    })
}

export const getCheckedRoles = (userId) => {
    return jwtServerInstance.request({
        url: '/sysuser/checkedroles',
        method: 'get',
        params: {
            userId: userId,
        }
    })
}


export const saveRoles = (userId, checkedroleIds) => {
    return jwtServerInstance.request({
        url: '/sysuser/saveroles',
        method: 'post',
        data: {
            userId: userId,
            checkedroleIds: checkedroleIds
        }
    })
}


//根据登录用户名加载该用户可以访问的菜单
export const getMenuTreeByUsername = () => {
    return jwtServerInstance.request({
        url: '/sysmenu/tree/user',
        method: 'post',
        params: {
            username: getTokenUser()  //从JWT中获取用户名
        }
    })
}

//JWT令牌刷新接口
export const refreshToken = () => {
    return jwtServerInstance.request({
        url: '/refreshtoken',
        method: 'get'
    })
}