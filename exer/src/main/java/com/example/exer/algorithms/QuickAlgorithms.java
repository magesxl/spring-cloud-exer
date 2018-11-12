package com.example.exer.algorithms;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickAlgorithms {

    public static void main(String... args) {
        int[] a = {3, 6, 18, 8, 2, 4};
        quickSort(0, a.length - 1, a);
  //      quickMidSort(0, a.length - 1, a);
        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int low, int high, int... a) {
        if (low >= high) return;
        int left = low;
        int right = high;
        //设置基准数
        int temp = a[left];
        while (left < right) {
            //探针从右往左边开始比较  如果右边小于基准  结束循环
            while (temp < a[right] && left < right) {
                right--;   //如果依次的数据都比基准数大   一直循环
            }

            //从左往右开始比较  如果左边大于基准  结束循环
            while (temp >= a[left] && left < right) {
                left++;
            }

            //交换两个探针位置
            int t = a[left];
            a[left] = a[right];
            a[right] = t;
        }
        System.out.println(Arrays.toString(a));
        System.out.println(left);
        //当左右探针相遇时，结束循环，然后交换基准值与探针位置的数值
        a[low] = a[left];  //把探针位置的值与基准值
        a[left] = temp;

        quickSort(low, left - 1, a);
        quickSort(left + 1, high, a);
    }

    /**
     * 去中间值排序
     */
    public static void quickMidSort(int low,int high,int... a){
        if(low>=high) return;
        int left = low;
        int right = high;
        //设置基准数
        int temp =  a[(right-left)/2];
        while (left != right) {
            //探针从右往左边开始比较  如果右边小于基准  结束循环
            while (temp < a[right] && left < right) {
                right--;   //如果依次的数据都比基准数大   一直循环
            }

            //从左往右开始比较  如果左边大于基准  结束循环
            while (temp >= a[left] && left < right) {
                left++;
            }

            //交换两个探针位置
            int t = a[left];
            a[left] = a[right];
            a[right] = t;
            System.out.println(Arrays.toString(a));
        }
        System.out.println(Arrays.toString(a));
        System.out.println(left);
        //当左右探针相遇时，结束循环，然后交换基准值与探针位置的数值
        a[(right-left)/2] = a[left];  //把探针位置的值与基准值
        a[left] = temp;
        quickMidSort(low, left - 1, a);
        quickMidSort(left + 1, high, a);
    }
}
