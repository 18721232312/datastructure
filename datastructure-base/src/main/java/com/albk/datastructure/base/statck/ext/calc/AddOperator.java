package com.albk.datastructure.base.statck.ext.calc;

/**
 * @author BK
 * @version V2.0
 * @description: 加法
 * @team: ALBK
 * @date 2018/4/5 23:24
 */
public class AddOperator implements IOperator {
    @Override
    public double execute(double left, double right) {
        return left + right;
    }
}
