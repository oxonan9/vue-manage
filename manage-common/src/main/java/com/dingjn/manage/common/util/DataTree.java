package com.dingjn.manage.common.util;

import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public interface DataTree<T> {
    //维护树形关系：元素一id
    Integer getId();

    //维护树形关系：元素二父id
    public Integer getParentId();

    //子节点数组
    public void setChildren(List<T> children);

    public List<T> getChildren();
}
