package com.ty.sort;

import java.util.Arrays;

/**
 * program : OneCode
 * description : 快速排序
 * author : jyf
 * date : 2020-08-06 11:16
 **/
public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        int[] arr = {0,1,0,0,1,1};

        int arr[] = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1); // 位移法-> 786 0.8S  800W 数据
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 快速排序
     *
     * @param arr   数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;  // 左节点
        int r = right;  // 右节点
        int pivot = arr[(left + right) / 2];  // 表示使用中心点
        int temp = 0; // 中间变量
        // 循环去交换左右两边的数
        while (l < r){
            // 看左边有没有大于中心点的数
            while (arr[l] < pivot){
                l++;
            }
            // 看右边有没有小于 中心点 的数
            while (arr[r] > pivot){
                r--;
            }
            // 最坏的情况就是 arr[l] = pivot 或者 arr[r] = pivot
            if (l >= r){
                // 说明在中心点了  左边已经小于中心点  右边已经都大于中心点了
                break;
            }

            // 否则就进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完了如果 右边的数正好到了中心点 也就是交换后的arr[l]
            if (arr[l] == pivot){
                r--;
            }
            // 如果交换了 左边的数正好到了中心点 也就是交换后的arr[r]
            if (arr[r] == pivot){
                l++;
            }
        }

        // 跳出循环说明两遍已经排序完成了
        if (l == r){
            l++;
            r--;
        }

        if (right > l){
            // 把右边给他
            quickSort(arr, l, right);
        }

        if (left < r){
            // 把左边给他
            quickSort(arr, left, r);
        }


    }

}
