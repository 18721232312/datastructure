package com.albk.datastructure.base.linkedlist;

/**
 * @author BK
 * @version V2.0
 * @description:
 * 双向链表在定位添加和删除时，可以先判断 位置 ，是处于左边还是右边
 * 如果处于左边则从头遍历。如果处于右边 ，则从属于开始遍历
 * 这是androidJDK， 谷歌 开发工程师的优化，与JDK sun工程师实现 不一样的地方
 * 查找
 * 排序
 * 反转
 * 去除重复元素
 * @team: ALBK
 * @date 2018/4/2 22:16
 */
public class SinglyLinkedList<T> {

    private int size = 0;

    private Node<T> root;

    private Node<T> tail;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T value) {
        Node<T> node = new Node<T>(value);
        if (root == null) {
            root = node;
            size++;
            tail = root;
        }
    }

    public void clean() {
        root = tail = null;
        size = 0;
    }

    /**
     * 添加方法
     *
     * @param value
     */
    public void add(T value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            tail = root;
        } else {
//            Node walker = root ;
//            while(walker.next!=null){
//                walker = walker.next;
//            }
//            walker.next = newNode;
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    public void traversal() {
        Node walker = root;
        while (walker != null) {
            System.out.println(walker.value);
            walker = walker.next;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<Integer>(-1);
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        linkedList.traversal();
        System.out.println("链表大小：" + linkedList.size());
        linkedList.remove(5);
        System.out.println("链表大小：" + linkedList.size());
        linkedList.traversal();

        linkedList.insert(5, 100);
        linkedList.traversal();

        linkedList.clean();

        linkedList.add(200);
        linkedList.traversal();
    }

    public void remove(int index) {
        if (index < 0 || index > size() + 1) {
            return;
        }
        Node walker = root;
        int start = 0;
        while (start + 1 < index) {
            start++;
            walker = walker.next;
        }
        walker.next = walker.next.next;
        size--;
    }


    public int size() {
//        Node walker = root ;
//        int res = 0;
//        while (walker.next!=null){
//            walker = walker.next ;
//            res ++;
//        }
//        return res;
        return size;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size() + 1) {
            throw new RuntimeException("position is error ");
        }
        int i = 0;
        Node walker = root;
        while (i + 1 != index) {
            walker = walker.next;
            i++;
        }
        Node newNode = new Node(value);
        Node after = walker.next;
        walker.next = newNode;
        newNode.next = after;
        size++;
    }


}
