package com.albk.datastructure.sort;

/**
 * @author BK
 * @version V2.0
 * @description: 堆排序
 * @team: ALBK
 * @date 2018/4/15 14:11
 */
public class HeapSort {


    /**
     * 堆排序
     * 先构造大顶堆，然后遍历数组，将大顶堆，第一个位置的大值，放到数组末尾，并对剩下的数组内容，进行重新构造大顶堆
     * 最终遍历完成，数组应该是一个升度排序的。
     * 如果需要降序排，可以构造小顶堆
     *
     * @param a 要排序的数组
     */
    public void heapSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        buildMaxHeap(a);
        for (int i = a.length - 1; i > 0; i--) {
            ArrayUtils.swap(a, 0, i);
            adjustHeap(a, i, 0);
        }
    }

    /**
     * 构造一个大顶堆
     * 根据树的特性， 只需要遍历一半即可，
     * 因为，父节点i的左子节点下标等于 2*i+1
     * 父节点i的右子节点下标等于 2*i+2
     * tips： 构造大顶堆 最好是从从下往上调整，避免每次调整后，都对下面的产生影响
     *
     * @param a
     */
    public void buildMaxHeap(int[] a) {
        int half = (a.length - 1) / 2;
        //从后往前遍历
        for (int i = half; i >= 0; i--) {
            adjustHeap(a, a.length, i);
        }
    }

    /**
     * 对堆进行调整 使其满足大顶堆的特性
     *
     * @param a
     * @param remainLength
     * @param i
     */
    private void adjustHeap(int[] a, int remainLength, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int maxIndex = i;
        if (left < remainLength && a[left] > a[i]) {
            maxIndex = left;
        }
        if (right < remainLength && a[right] > a[maxIndex]) {
            maxIndex = right;
        }
        //如果最大值不是当前位置 ，则交换则意味着发生了交换，
        //被交换的子节点 因为值发生了变化，可能不满足条件，所以 应该该对发生变化的节点再次进行调整
        if (i != maxIndex) {
            ArrayUtils.swap(a, i, maxIndex);
            adjustHeap(a, remainLength, maxIndex);
        }
    }
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = new int[]{102, 3, 5, 52, 6, 777, 88, 3, 32, 23, 15};
        heapSort.heapSort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }
}
