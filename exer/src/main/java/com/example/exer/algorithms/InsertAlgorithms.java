package com.example.exer.algorithms;

import java.util.Arrays;

/**
 * 插入排序算法
 * 设有一组关键字｛K1， K2，…， Kn｝；排序开始就认为 K1 是一个有序序列；让 K2 插入上述表长为 1 的有序序列，使之成为一个表长为 2 的有序序列；
 * 然后让 K3 插入上述表长为 2 的有序序列，使之成为一个表长为 3 的有序序列；
 * 依次类推，最后让 Kn 插入上述表长为 n-1 的有序序列，得一个表长为 n 的有序序列。
 */
public class InsertAlgorithms {

    public static void main(String... args) {
        int[] a = {1, 6, 3, 8, 2, 4};
        insertSort(a);
        //insertSort1(a);
        //binarySort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 具体算法描述如下：
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤 3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置后
     * 重复步骤 2~5
     */
    public static void insertSort(int[] a) {
        //数组第一位已排好序    和后面的数值比较  如果小于
        for (int i = 1; i < a.length; i++) {
            //拿第二位元素开始做临时值
            if (a[i - 1] > a[i]) {//注意[0,i-1]都是有序的。如果待插入元素比arr[i-1]还大则无需再与[i-1]前面的元素进行比较了，反之则进入if语句
                int temp = a[i];
                int j;
                for (j = i - 1; j > 0 && temp < a[j]; j--) {
                    a[j + 1] = a[j];   //把比temp大或相等的元素全部往后移动一个位置
                }
                a[j + 1] = temp;  //把待排序的元素temp插入腾出位置的(j+1)
                System.out.println(Arrays.toString(a));
            }
        }
    }

    public static void insertSort1(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (a[j - 1] <= a[j]) {
                    break;
                }
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
                System.out.println(Arrays.toString(a));
            }
        }
    }

    /**
     * 查找待排序元素该插入位置
     *
     * @param a
     * @param low
     * @param high
     * @param temp
     * @return
     */
    public static int binarySearch(int[] a, int low, int high, int temp) {
        int mid = 0;
        int ret = -1;
        while (low <= high) {
            //有序数组的中间坐标，此时用于二分查找，减少查找次数
            //直接平均可能會溢位，所以用此算法
            //mid = (low + high) / 2;  错误写法  会造成数据溢出
            mid = low + (high - low) / 2;
            //如果有序序列中的中间元素大于待排序的元素，则有序序列的中间坐标向前搜索，否则向后搜索
            if (a[mid] < temp && temp <= a[mid + 1]) {
                ret = mid + 1;
                break;
            } else if (a[mid] < temp) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ret;
    }

    public static void binarySort(int[] a) {
        int i, j, k, temp;
        for (i = 1; i < a.length; i++) {
            //将待插入的元素赋给temp，这个元素前面是有序数组，用于插入到有序数组中
            temp = a[i];
            if (a[i] < a[0]) {
                k = 0;
            } else {
                k = binarySearch(a, 0, a.length, temp);
            }
            //j首先赋值为要插入值的前一个元素的最后的坐标，也就是有序数组中最后一个元素的坐标
            // 然后依次向前扫描有序数组，然后如果满足条件则向后移动数据
            for (j = i; j > k; j--) {
                a[j] = a[j - 1];
            }
            //将待排序的元素插入到array数组中
            a[k] = temp;
            System.out.println(Arrays.toString(a));
        }
    }
}
