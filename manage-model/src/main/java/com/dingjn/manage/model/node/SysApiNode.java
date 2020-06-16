package com.dingjn.manage.model.node;


import com.dingjn.manage.common.util.DataTree;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public class SysApiNode extends SysApi implements DataTree<SysApiNode> {
    //为某对象加上children成员变量
    private List<SysApiNode> children;

    @Override
    public Integer getParentId() {
        return super.getApiPid();
    }

    //set和get方法，为children赋值取值
    @Override
    public void setChildren(List<SysApiNode> children) {
        this.children = children;
    }

    @Override
    public List<SysApiNode> getChildren() {
        return this.children;
    }
}