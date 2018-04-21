package com.albk.datastructure.algorithm.qeuue;

/**
 * @author BK
 * @version V2.0
 * @description: 8皇后问题  回溯法
 * @team: ALBK
 * @date 2018/4/21 17:57
 */
public class Empress {


    private int queenNum = 8;//皇后的数量
    private int result = 0;//已经找以的解法的数量
    /**
     * 下标表示，第几列，值表示，第当前列的皇后放在了行为值的位置
     *
     * queenColPosition[3]=5 表示 第三列的皇后放到了第五行
     */
    private int[] queenColPosition = new int[queenNum];

    public Empress() {

    }

    /**
     * 在棋盘上放皇后
     */
    public void putQueen(int n) {
        boolean[] row = new boolean[queenNum];
        for (int i = 0; i < n; i++) {
            row[queenColPosition[i]] = true;
            int diff = n - i;
            //处理正斜路径
            if (queenColPosition[i] - diff >= 0) {
                row[queenColPosition[i] - diff] = true;
            }
            //处理反斜路径
            if (queenColPosition[i] + diff < queenNum) {
                row[queenColPosition[i] + diff] = true;
            }
        }

        for (int i = 0; i < queenNum; i++) {
            //如是当前行可以放值
            if (!row[i]) {
                //在queenColPosition记录皇后存放的位置i
                queenColPosition[n] = i;
                //如果N没有到最后一列，则继续递归往棋盘中的下一列放皇后
                //否则 ，n已经是最后一列，则表示 当前方案可行。对结果数量增加1，表示找到一套方案，同时把方案打印出来。
                //下面if else 的内容是回溯方案的核心
                if (n < queenNum - 1) {
                    putQueen(n + 1);
                } else {
                    result++;
                    print();
                }
            }
        }
    }

    private void print() {
        System.out.println("第" + result + "套方案是：");
        for (int i = 0; i < queenNum; i++) {
            for (int j = 0; j < queenNum; j++) {
                if (i == queenColPosition[j]) {
                    System.out.print(" \\ ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Empress empress = new Empress();
        empress.putQueen(0);
    }
}


