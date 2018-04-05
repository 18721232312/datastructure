package com.albk.datastructure.base.linkedlist;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team: ALBK
 * @date 2018/4/2 22:23
 */
public class Node<T> {
    public T value;

    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
