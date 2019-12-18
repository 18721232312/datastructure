package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @description: 选择排序 ， 复杂度N^2  不稳定
 * @date 2019-12-18 23:08
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {9, 1, 5, 13, 56, 2, 4, 100};
        long start = System.currentTimeMillis();
        //        sort1(arr);
        sort2(arr);
        System.out.println("cost time  " + (System.currentTimeMillis() - start));
    }

    private static void sort2(int[] arr) {

        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {

            int minPos = i;
            int maxPos = arr.length - i - 1;
            for (int j = i; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
                if (arr[j] > arr[maxPos]) {
                    maxPos = j;
                }
            }
            swap(arr, i, minPos);
            swap(arr, arr.length - 1 - i, maxPos);
        }
        print(arr);

    }

    private static void sort1(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }
            swap(arr, i, minPos);
        }

        print(arr);
    }

    private static void print(int[] arr) {
        for (int f : arr) {
            System.out.println(f);
        }
    }

    private static void swap(int[] arr, int i, int minPos) {
        int temp = arr[i];
        arr[i] = arr[minPos];
        arr[minPos] = temp;
    }
}
