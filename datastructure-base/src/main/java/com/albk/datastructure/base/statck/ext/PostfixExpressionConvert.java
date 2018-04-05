package com.albk.datastructure.base.statck.ext;

import com.albk.datastructure.base.statck.Stack;
import com.albk.datastructure.base.statck.ext.calc.OperatorEnum;

/**
 * @author BK
 * @version V2.0
 * @description: 后缀表达式转换器
 * @team: ALBK
 * @date 2018/4/5 21:33
 */
public class PostfixExpressionConvert {

    /**
     *  将中缀表达式转成后缀表达式
     */
    public static String convert(String expression) {
        if(expression==null || "".equals(expression)){
            return "";
        }
        StringBuilder res = new StringBuilder();
        Stack<String> stack = new Stack();
        String[] arr = expression.split(" ");
        for (int i = 0; i < arr.length; i++) {
            String currStr = arr[i];
            if (!OperatorEnum.isSymbol(currStr)) {
                res.append(currStr).append(" ");
            } else {
                if (stack.isEmpty()) {
                    stack.push(currStr);
                } else {
                    String lastSymbolStr = stack.peek();
                    OperatorEnum lastSymbol = OperatorEnum.valueOfSymble(lastSymbolStr);
                    OperatorEnum currSymbol = OperatorEnum.valueOfSymble(currStr);
                    if (currStr.equals(OperatorEnum.right_parenthesis.getSymbol())) {
                        while (!stack.peek().equals(OperatorEnum.left_parenthesis.getSymbol())) {
                            res.append(stack.pop()).append(" ");
                        }
                        stack.pop();
                    } else if (OperatorEnum.compare(currSymbol, lastSymbol)) {
                        i++;
                        res.append(arr[i]).append(" ");
                        res.append(currStr).append(" ");
                        while (!stack.isEmpty()) {
                            res.append(stack.pop()).append(" ");
                        }
                    } else {
                        stack.push(currStr);
                    }
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String express = "9 + ( 3 - 1 ) * 3 + 10 / 2";
        System.out.println(convert(express));
    }

}
