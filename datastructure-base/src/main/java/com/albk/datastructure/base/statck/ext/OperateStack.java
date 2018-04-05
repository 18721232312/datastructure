package com.albk.datastructure.base.statck.ext;

import com.albk.datastructure.base.statck.Stack;
import com.albk.datastructure.base.statck.ext.calc.Calculator;
import com.albk.datastructure.base.statck.ext.calc.OperatorEnum;

/**
 * @author BK
 * @version V2.0
 * @description: 操作数栈
 * @team: ALBK
 * @date 2018/4/5 14:48
 */
public class OperateStack {
    private Stack<Double> stack = new Stack<>();
    /***
     * 运行后缀表达式
     * @param expression  中缀表达式
     * @return  运行结果
     */
    public Double run(String expression ){
        //运行结果
        double res = 0 ;
        String postfixExpression  = PostfixExpressionConvert.convert(expression);
        if(expression==null || "".equals(expression)){
            return res;
        }
        //表达式左和右侧值
        double left ,right;
        for (String operand : postfixExpression.split(" ")) {
            if (!OperatorEnum.isSymbol(operand)) {
                stack.push(Double.valueOf(operand));
            } else {
                right = Double.valueOf(String.valueOf(stack.pop()));
                left = Double.valueOf(String.valueOf(stack.pop()));
                Calculator calculator =  new Calculator(OperatorEnum.valueOfSymble(operand));
                res = calculator.calc(left,right);
                stack.push(res);
            }
        }
        return res ;
    }
    public static void main(String[] args) {
        String calStr = "9 + ( 3 - 1 ) * 3 + 10 / 2";
        OperateStack os = new OperateStack();
        double res = os.run(calStr);
        System.out.println(res);
    }

}
