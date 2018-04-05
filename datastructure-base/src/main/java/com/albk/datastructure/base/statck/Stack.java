package com.albk.datastructure.base.statck;

/**
 * @author BK
 * @version V2.0
 * @description: 自定义栈实现   线程不安全
 * @team: ALBK
 * @date 2018/4/3 0:04
 */
public class Stack<T> {
    /**
     * 当前栈的大小
     */
    private int size;
    /**
     * 栈底
     */
    private StackFrame bottom;
    /**
     * 栈顶
     */
    private StackFrame top;

    private Stack() {
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(T value) {
        StackFrame sf = new StackFrame(value);
        if (bottom == null) {
            bottom = sf;
            top = bottom;
        } else {
            sf.next = top;
            top = sf;
        }
        size++;
    }

    /**
     * 栈大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return bottom == top;
    }

    /**
     * 清空栈
     */
    public void clear() {
        top = null;
        bottom = top;
    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {
        if (!isEmpty()) {
            T value = (T) top.value;
            top = top.next;
            size--;
            return value;
        }
        return null;
    }

    /**
     * 遍历栈
     */
    public void traversal() {
        StackFrame sf = top;
        while (sf != bottom) {
            System.out.println(sf.value);
            sf = sf.next;
        }
        System.out.println(sf.value);
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int i = 1; i <= 20; i++) {
            stack.push(i);
        }
        System.out.println("stack size " + stack.size());
        stack.traversal();

        System.out.println("pop:" + stack.pop());
        System.out.println("大小" + stack.size);
        System.out.println("pop:" + stack.pop());
        System.out.println("大小" + stack.size);
        System.out.println("pop:" + stack.pop());
        System.out.println("大小" + stack.size);
        System.out.println("pop:" + stack.pop());
        System.out.println("大小" + stack.size);
        System.out.println("pop:" + stack.pop());
        System.out.println("pop:" + stack.pop());
        System.out.println("大小" + stack.size);
        stack.clear();
        System.out.println("+++++++++++++++++++++++++++++++++");
        stack.traversal();
    }
}
