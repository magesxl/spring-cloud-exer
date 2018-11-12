package com.example.exer.algorithms;

import java.util.Arrays;

/**
 * 冒泡排序算法
 */
public class MaoPaoAlgorithms {
    public static void main(String... args) {
        int[] a = {1, 6, 3, 8, 2, 4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int... a) {
        //冒泡排序  前后两两相比较   如果前边比后变大 则交换
        //控制循环次数
        for (int i = 0; i < a.length - i; i++) {
            //减少外循环的次数   判断在一次外循环中，数据有没有进行交换
            boolean flag = true;
            //控制两两比较次数
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = false;
                    System.out.println("Sorting: " + Arrays.toString(a));
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 每次外循环都会将最大的数移到最后的位置，随着循环的增加，后面的数其实已经是排好序的，而下一次循环又得进行无用的比较
     * 标记循环里最后一次发生数据交换的位置
     */
}
