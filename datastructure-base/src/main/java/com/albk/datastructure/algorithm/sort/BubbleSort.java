package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @description:
 * @date 2019-12-18 23:33
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 88, 4, 9, 5, 22, 101};
        sort(arr);
        ArrayUtils.print(arr);
    }

    private static void sort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }
}
