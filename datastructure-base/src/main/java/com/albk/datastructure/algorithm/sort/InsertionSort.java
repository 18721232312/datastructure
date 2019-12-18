package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @description: 插入排序
 * @date 2019-12-18 23:24
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 88, 4, 9, 5, 22, 101};
        sort(arr);
    }

    private static void sort(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    ArrayUtils.swap(arr, j, j - 1);
                }
            }
        }
        ArrayUtils.print(arr);
    }
}
