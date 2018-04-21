package com.albk.datastructure.algorithm;

/**
 * @author BK
 * @version V2.0
 * @description: 大数相乘  模拟乘法运算
 * @team: ALBK
 * @date 2018/4/21 23:27
 */
public class BigNumberMulti {
    public String multi(String s1, String s2) {
        StringBuffer sb1 = new StringBuffer(s1);
        StringBuffer sb2 = new StringBuffer(s2);

        char[] s1Array = sb1.reverse().toString().toCharArray();
        char[] s2Array = sb2.reverse().toString().toCharArray();

        int l1 = s1Array.length;
        int l2 = s2Array.length;

        int[] result = new int[l1 + l2 + 3];
        //保存各位相乘的结果
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                result[i + j] += Integer.valueOf(String.valueOf(s1Array[i])) * Integer.valueOf(String.valueOf(s2Array[j]));
            }
        }
        //处理各位的进位
        for (int i = 0; i < result.length; i++) {
            int element = result[i];
            int p1 = element / 10;
            int p2 = element % 10;
            result[i] = p2;
            if (p1 != 0) {
                result[i + 1] += p1;
            }
        }
        //找到最高位的有效位的位置
        int validPosition = 0;
        for (int x = result.length - 1; x >= 0; x--) {
            if (result[x] != 0) {
                validPosition = x;
                break;
            }
        }
        StringBuilder res = new StringBuilder();
        System.out.println("最终的结果为：");
        //从最高位有效位依次拼接出最终的结果
        for (int i = validPosition; i >= 0; i--) {
            res.append(result[i]);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BigNumberMulti bigNumberMulti = new BigNumberMulti();
        System.out.println(bigNumberMulti.multi("123456789", "123456789"));
    }
}
