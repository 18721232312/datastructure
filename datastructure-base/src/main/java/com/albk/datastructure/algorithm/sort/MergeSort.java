package com.albk.datastructure.algorithm.sort;

/**
 * @author BK
 * @version V2.0
 * @description: 归并排序
 * @team: ALBK
 * @date 2018/4/15 12:34
 */
public class MergeSort {
    public void mergeSort(int[] array, int start, int end) {
        if(start >= end) return ;
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        merge(array, start, mid, end);
    }
    /**
     * 合并 类似于合并 两个链表
     */
    private void merge(int[] array, int left, int mid, int right) {
        int[] tempArray = new int[array.length];
        int leftPoint = left;
        int rightPoint = mid + 1;
        int tempPoint = leftPoint;
        while (leftPoint <= mid && rightPoint <= right) {
            if (array[leftPoint] <= array[rightPoint]) {
                tempArray[tempPoint++] = array[leftPoint++];
            } else {
                tempArray[tempPoint++] = array[rightPoint++];
            }
        }
        while (leftPoint <= mid) {
            tempArray[tempPoint++] = array[leftPoint++];
        }
        while (rightPoint <= right) {
            tempArray[tempPoint++] = array[rightPoint++];
        }
        while(left<=right){
            array[left] = tempArray[left++];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{102, 3, 5, 52, 6, 777, 88, 3, 32, 23, 15};
        MergeSort ms = new MergeSort();
        ms.mergeSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }
    }

}