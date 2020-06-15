package com.dingjn.manage.model.node;


import com.dingjn.manage.common.util.DataTree;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public class SysMenuNode extends SysMenu implements DataTree<SysMenuNode> {
    //为某对象加上children成员变量
    private List<SysMenuNode> children;

    @Override
    public Integer getParentId() {
        //因为不同的人设计model或者数据库，
        //父id字段叫不同的名字，pid或parentId或org_pid等，
        //这里适配一下，统一为使用getParentId，获取父id数据
        return super.getMenuPid();
    }
    //set和get方法，为children赋值取值
    @Override
    public void setChildren(List<SysMenuNode> children) {
        this.children = children;
    }

    @Override
    public List<SysMenuNode> getChildren() {
        return this.children;
    }
}