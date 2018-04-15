package com.albk.datastructure.sort;

/**
 * @author BK
 * @version V2.0
 * @description: 数组工具类
 * @team: ALBK
 * @date 2018/4/15 14:21
 */
public class ArrayUtils {
    /**
     * 交换组中两个下标中的元素
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
