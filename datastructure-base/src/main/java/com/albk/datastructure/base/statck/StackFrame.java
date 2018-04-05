package com.albk.datastructure.base.statck;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @team: ALBK
 * @date 2018/4/3 0:05
 */
public class StackFrame<T> {

    public T value;

    public StackFrame<T> next;

    public StackFrame() {
    }

    public StackFrame(T value) {
        this.value = value;
    }
}
