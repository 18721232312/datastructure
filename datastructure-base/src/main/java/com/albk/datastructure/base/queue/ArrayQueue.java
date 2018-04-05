package com.albk.datastructure.base.queue;

/**
 * @author BK
 * @version V2.0
 * @description: 静态队列，环形数组  tail = (tail +  1 ) % length
 * 线程 不安全
 * 扩展点： 尝试 实现线程安全，阻塞队列
 * @team: ALBK
 * @date 2018/4/3 23:14
 */
public class ArrayQueue<E> {
    private Object[] values;

    private int size;
    private int head;

    private int tail;

    private ArrayQueue(int capacity) {
        values = new Object[capacity + 1];
        head = 0;
        tail = 0;
    }

    /**
     * 入队列
     *
     * @param e
     */
    public void offer(E e) {
        if (!isFull()) {
            values[tail] = e;
            tail = ((tail + 1) % values.length);
            size++;
        } else {
            System.out.println("队列已经满!");
        }
    }

    /**
     * 环形数据中，如果头和尾指向同一个位置时，说明队列是空的
     *
     * @return
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 队列是否已满
     * 如果尾巴下一个就是头部，说明队列已满
     *
     * @return
     */
    public boolean isFull() {
        return (tail + 1) % values.length == head;
    }

    /**
     * 求队列数据大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 出队列
     */
    public E poll() {
        if (!isEmpty()) {
            E e = (E) values[head];
            head = (head + 1) % values.length;
            size--;
            return e;
        }
        return null;
    }

    /**
     * 遍历队列数据
     */
    public void travesal() {
        int i = head;
        while (i != tail) {
            System.out.println(values[i]);
            i = (i + 1) % values.length;
        }
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue(5);
        for (int i = 1; i <= 5; i++) {
            queue.offer("data:" + i);
        }
        queue.travesal();
        System.out.println("出队列:" + queue.poll());
        System.out.println("队列中有多个少数据：" + queue.size());
        queue.travesal();
    }
}
