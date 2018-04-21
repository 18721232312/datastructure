package com.albk.datastructure.algorithm;

/**
 * @author BK
 * @version V2.0
 * @description: 约瑟芬杀人法
 * 使用循环链表表示每一个人首尾相连构成一个圈
 * @team: ALBK
 * @date 2018/4/21 22:25
 */
public class Josephine {
    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private int gap;
    private Node header;

    public Josephine(int gap, Node root) {
        this.gap = gap;
        this.header = root;
    }

    public void startGame() {
        Node walker = header ;
        while (walker.next!=walker){
            for (int i = 1; i < gap-1; i++) {
                walker = walker.next;
            }
            System.out.println("第"+walker.next.value+"个人已经出局");
            walker.next = walker.next.next;
            walker = walker.next;
        }
        System.out.println("幸存者是：" +walker.value);

    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node point = root;
        for (int i = 2; i <= 20; i++) {
            point.next = new Node(i);
            point = point.next;
        }
        point.next = root;
        Josephine josephine = new Josephine(5,root );
        josephine.startGame();
    }
}
