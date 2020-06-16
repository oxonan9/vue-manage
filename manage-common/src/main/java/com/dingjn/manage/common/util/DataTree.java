package com.dingjn.manage.common.util;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc: 树状数据接口，因为有很多需要返回树状
 */
public interface DataTree<T> {
    //元素的Id
    Integer getId();

    //元素的父Id
    //父id字段可能叫不同的名字，pid或parentId或org_pid等，
    //这里适配一下，统一为使用getParentId，获取父id数据
    Integer getParentId();

    //子节点集合
    void setChildren(List<T> children);

    List<T> getChildren();
}
