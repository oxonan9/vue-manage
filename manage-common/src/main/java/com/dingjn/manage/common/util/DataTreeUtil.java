package com.dingjn.manage.common.util;


import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: dingjn
 * @Desc:
 */
public class DataTreeUtil {

    public static <T extends DataTree<T>> List<T> buildTree(
            List<T> paramList,Integer rootNodeId) {
        List<T> returnList = new ArrayList<T>();
        for (T node : paramList) {//查找根节点
            if(node.getId().equals(rootNodeId) ){
                returnList.add(node);
            }
        }
        //递归为children赋值
        for (T entry : paramList) {
            toTreeChildren(returnList,entry);
        }
        return returnList;
    }

    private static <T extends DataTree<T>> void toTreeChildren(List<T> returnList, T entry) {
        for (T node : returnList) {   //根节点 1
            if(entry.getParentId().equals(node.getId())){  //如果parentId等于 1，那就添加到字节点中
                if(node.getChildren() == null){   //没有创建
                    node.setChildren(new ArrayList<T>());
                }
                node.getChildren().add(entry);  //有就直接添加  childer = 2
            }
            if(node.getChildren() != null){   //如果不为 null再递归查找2的字节点，因为你都为null了，说明了
                toTreeChildren(node.getChildren(),entry);
            }
        }
    }
}