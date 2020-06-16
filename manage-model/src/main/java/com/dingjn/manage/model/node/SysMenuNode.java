package com.dingjn.manage.model.node;


import com.dingjn.manage.common.util.DataTree;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public class SysMenuNode extends SysMenu implements DataTree<SysMenuNode> {

    private List<SysMenuNode> children;

    private String path;   //新加入path
    private String name;  //新加入name

    public String getPath() {
        return this.getUrl();   //path返回url
    }
    public String getName() {
        return this.getMenuName();  //name返回menuName
    }

    @Override
    public Integer getParentId() {
        return super.getMenuPid();
    }
    @Override
    public void setChildren(List<SysMenuNode> children) {
        this.children = children;
    }
    @Override
    public List<SysMenuNode> getChildren() {
        return this.children;
    }
}