package com.albk.datastructure.base.statck.ext.calc;

/**
 * @author BK
 * @version V2.0
 * @description: 计算器
 * @team: ALBK
 * @date 2018/4/5 23:26
 */
public class Calculator {

    private IOperator operator;

    public Calculator(OperatorEnum operatorEnum) {
        if (operatorEnum == OperatorEnum.add) {
            operator = new AddOperator();
        } else if (operatorEnum == OperatorEnum.minus) {
            operator = new MinusOperator();
        } else if (operatorEnum == OperatorEnum.multiply) {
            operator = new MultiplyOperator();
        } else if (operatorEnum == OperatorEnum.divide) {
            operator = new DivOperator();
        }
    }

    public double calc(double left, double right) {
        return operator.execute(left, right);
    }
}
