package com.albk.datastructure.base.statck.ext.calc;

/**
 * @author BK
 * @version V2.0
 * @description: 操作符枚举
 * @team: ALBK
 * @date 2018/4/5 14:51
 */
public enum OperatorEnum {
    add("+", 1),
    minus("-", 1),
    multiply("*", 2),
    divide("/", 2),
    left_parenthesis("(", 1),
    right_parenthesis(")", 1);
    private String symbol;
    private int priority;

    OperatorEnum(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public static OperatorEnum valueOfSymble(String c) {
        for (OperatorEnum operatorEnum : OperatorEnum.values()) {
            if (operatorEnum.getSymbol().equals(c)) {
                return operatorEnum;
            }
        }
        return null;
    }

    public static boolean isSymbol(String c) {
        for (OperatorEnum operatorEnum : OperatorEnum.values()) {
            if (operatorEnum.getSymbol().equals(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较两个操作符的优先级大小
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean compare(OperatorEnum a, OperatorEnum b) {
        return a.priority > b.priority;
    }
}
