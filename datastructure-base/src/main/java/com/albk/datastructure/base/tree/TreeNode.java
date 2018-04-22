package com.albk.datastructure.base.tree;

/**
 * @author BK
 * @version V2.0
 * @description: 树
 * @team: ALBK
 * @date 2018/4/6 17:14
 */
public class TreeNode<T> {
    public T value;
    public TreeNode<T> left ;
    public TreeNode<T> right ;

    public TreeNode(T value) {
        this.value = value;
    }

}
