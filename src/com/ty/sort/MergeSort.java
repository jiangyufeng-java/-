package com.ty.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * program : OneCode
 * description : 归并排序
 * author : jyf
 * date : 2020-08-07 13:53
 **/
public class MergeSort {

    public static void main(String[] args) {
//        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int arr[] = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }


    // 分治
    /**
     * @param arr   原始数组
     * @param left  最左边的索引
     * @param right 最右边的索引
     * @param temp  临时数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; // 这是中心点
            // 分割两遍的数组
            // 左边去分割
            mergeSort(arr, left, mid, temp);
            // 右边去分割
            mergeSort(arr, mid + 1, right, temp);
            // 去排序
            merge(arr,left, mid, right, temp);
        }

    }

    // 合并数组
    /**
     * @param arr   原始数组
     * @param left  最左边的索引
     * @param mid   中间索引
     * @param right 最右边的索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int midLeft = mid +1; // 表示另外一个数组的开始索引
        int n = 0; // 表示临时数组的索引
        while (l <= mid && midLeft <= right){
            // 如果左边的数小于另外一个数组的数
            if (arr[l] <= arr[midLeft] ){
                // 把左边这个数放入新数组.
                temp[n] = arr[l];
                l++;
                n++;
            }else{
                // 反之 把右边这个数放入新数组
                temp[n] = arr[midLeft];
                midLeft++;
                n++;
            }
        }

        // 可能会有一边数组还没有放完
        // 左边数组没放完
        while (l <= mid){
            temp[n] = arr[l];
            l++;
            n++;
        }
        // 右边数组没放完
        while ( midLeft <= right){
            temp[n] = arr[midLeft];
            midLeft++;
            n++;
        }

        // 进行转移
        n = 0;
        // 因为起始位置可能是不一样的
        // 第一次合并是left = 0  right = 1
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[n];
            n++;
            tempLeft++;
        }
    }

}
