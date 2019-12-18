package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @description: 希尔排序  n^1.3
 * h=1
 * h=3*h+1 最好的间隔序列， h>整个长度的1/3不合适了
 * @date 2019-12-18 23:48
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {9, 1, 5, 13, 56, 2, 4, 100, 14, 66, 22, 7};
        long start = System.currentTimeMillis();
        sort(arr, 4);
        ArrayUtils.print(arr);
    }

    private static void sort(int[] arr, int gap) {
        if (arr == null || gap == 0) {
            return;
        }
        //计算最优的间隔h
        int h = 1;
        while (h * 3 + 1 <= arr.length) {
            h = h * 3 + 1;
        }
        for (int g = h; g > 0; g--) {//间隔每次缩小1
            for (int i = 0; i < arr.length / g; i++) { //控制插入排序的外层循环次数
                for (int j = i; j <= arr.length; j += g) { //每次增加步长
                    //插入排序比较
                    //但是需要判断边界内层循环j增加步长不能超过数组长度， 和 arr[j]比较的前一个位置>=0，不超过正常范围
                    if (j < arr.length && j - g >= 0 && arr[j] < arr[j - g]) {
                        ArrayUtils.swap(arr, j, j - g);
                    }
                }
            }
        }
    }
}
