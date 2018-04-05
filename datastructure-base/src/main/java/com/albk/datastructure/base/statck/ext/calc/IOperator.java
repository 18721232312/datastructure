package com.albk.datastructure.base.statck.ext.calc;

/**
 * @author BK
 * @version V2.0
 * @description: 抽象运算符
 * @team: ALBK
 * @date 2018/4/5 23:22
 */
public interface IOperator {

    /**
     * 运行
     *
     * @param left
     * @param right
     * @return
     */

    double execute(double left, double right);
}
