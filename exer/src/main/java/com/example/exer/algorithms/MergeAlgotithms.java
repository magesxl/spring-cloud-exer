package com.example.exer.algorithms;

public class MergeAlgotithms {
    /**
     * 相邻两个有序序列归并成一个有序序列的过程
     * 原数组   需要归并的数组
     * 新数组   归并后的数组
     * 两个有序性序列的第一个有序序列的第一个元素的下标
     * 两个有序序列的第一个有序序列的最后一个元素的下标
     * 两个有序序列的第二个有序序列的最后一个元素的下标
     */
    public void mergeSort(int[] oldArray, int[] newArray, int oneFirstIndex, int oneLastIndex, int twoLastIndex) {
        int oneBegin = oneFirstIndex;           //两个有序序列的第一个有序序列的第一个元素的下标
        int twoBegin = oneLastIndex + 1;        //两个有序序列的第二个有序序列的第一个元素的下标
        int newAlo = oneFirstIndex;             //新数组中的指向
        while (oneBegin <= oneLastIndex && twoBegin <= twoLastIndex) {  //两个有序序列都没有遍历完
            if (oldArray[oneBegin] < oldArray[twoBegin]) {
                newArray[newAlo++] = oldArray[oneBegin++];
            } else {
                newArray[newAlo++] = oldArray[twoBegin++];
            }
        }

        //当其中一个序列遍历完之后，将剩下那个序列加到新数组中,判断是哪一个序列没有遍历完
        while (oneFirstIndex <= oneLastIndex) {
            newArray[newAlo++] = oldArray[oneFirstIndex++];
        }

        while (twoBegin <= twoLastIndex) {
            newArray[newAlo++] = oldArray[twoBegin++];
        }
    }

}
