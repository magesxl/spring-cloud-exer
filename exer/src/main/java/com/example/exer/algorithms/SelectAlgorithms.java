package com.example.exer.algorithms;

import java.util.Arrays;

/**
 * 选择排序算法
 */
public class SelectAlgorithms {

    public static void main(String... args) {
        int[] a = {1, 6, 3, 8, 2, 4};
        selectSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 1. 从待排序序列中，找到关键字最小的元素；
     * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
     * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
     *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     */
    public static void selectSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            //需要在位置i的后边开始
            // 第i个和第j个比较j可以取到最后一位，所以要用j<=array.length-1
            for(int j=i+1;j<a.length-1;j++){
                // 注意和冒泡排序的区别，这里是i和j比较。  从第一个和后续依次比较
                if (a[i] >= a[j]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }
}
