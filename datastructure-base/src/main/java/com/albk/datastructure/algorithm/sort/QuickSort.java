package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @version V2.0
 * @description: 快速排序
 * @team: ALBK
 * @date 2018/4/15 11:26
 */
public class QuickSort {
    /**
     * 从两端扫描交换的方式
     *
     * @param arrays
     * @param start
     * @param end
     */
    private void sort(int[] arrays, int start, int end) {
        if (start > end) return;
        int pivot = arrays[start];
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while (left <= right && arrays[left] <= pivot) {
                left++;
            }
            while (left <= right && arrays[right] >= pivot) {
                right--;
            }
            if (left <= right) {
                swap(arrays, left, right);
            }
        }
        swap(arrays, start, right);
        sort(arrays, start, right - 1);
        sort(arrays, right + 1, end);
    }

    /**
     * 两端扫描，一端挖坑，另一端填补
     *
     * @param arrays
     * @param start
     * @param end
     */
    private void sort2(int[] arrays, int start, int end) {
        if (start > end) return;
        int pivot = arrays[start];
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while (left <= right && arrays[right] >= pivot) {
                right--;
            }
            arrays[left] = arrays[right];
            while (left <= right && arrays[left] <= pivot) {
                left++;
            }
            arrays[right] = arrays[left];
        }
        arrays[left] = pivot;
        sort(arrays, start, left - 1);
        sort(arrays, left + 1, end);
    }


    private void swap(int[] array, int start, int end) {
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] array = new int[]{102, 3, 5, 52, 6, 777, 88, 3, 32, 23, 15};
        qs.sort(array, 0, array.length - 1);
        print(array);
        qs.sort2(array, 0, array.length - 1);
        print(array);
    }

    private static void print(int[] array) {
        for (int i : array) {
            System.out.println(i);
        }
        System.out.println("========================打印完毕！");
    }
}
