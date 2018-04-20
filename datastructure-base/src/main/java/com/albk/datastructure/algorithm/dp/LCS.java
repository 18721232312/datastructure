package com.albk.datastructure.algorithm.dp;

/**
 * @author BK
 * @version V2.0
 * @description: 求最长子序列
 * @team: ALBK
 * @date 2018/4/21 23:09
 */
public class LCS {

    /**
     * 状态定义 ： dp[i][j]    ;   i :代表字符串A中的位置 , j  代表字符串中B的位置
     * 状态转移方程： dp[i][j]
     * i==j  = 0 :  dp[i][j]=0
     * i==j and <>0  dp[j][j] = dp[i-1][j-1]+1
     * i<>j  dp[i][j] =  Maxth.max ( dp[i-1][j] , dp [i][j-1])
     *
     * @param left
     * @param right
     * @return
     */
    public int lcs(String left, String right) {
        int[][] dp = new int[left.length() + 1][right.length() + 1];
        char[] l = left.toCharArray();
        char[] r = right.toCharArray();
        //把dp的第0行和第0列，作为dummy数据
        for (int i = 1; i <= l.length; i++) {
            for (int j = 1; j <= r.length; j++) {
                if (l[i - 1] == r[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[l.length][r.length];
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        System.out.println(lcs.lcs("randomxx ", "andxx"));
    }
}
