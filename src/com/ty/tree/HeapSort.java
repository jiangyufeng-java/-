package com.ty.tree;

import java.util.Arrays;

/**
 * program : OneCode
 * description : 堆排序
 * author : jyf
 * date : 2020-08-18 11:52
 **/
public class HeapSort {

    public static void main(String[] args) {
        //int[] arr = {4, 6, 8, 5, 9};
        // 进行性能测试
        int arr[] = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*arr.length);
        }
        long start = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);  // 800W 只需要1.6S
    }

    /**
     * 对数组进行堆排序
     * @param arr  需要排序的数组
     */
    public static void heapSort(int[] arr) {
        // 1. 首先我们要把当前数组转成一个大顶堆
        // arr.length/2-1 为公式, 求出当前最大的非叶子节点
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, arr.length, i);
        }
        // 2.我们需要对转换后的进行调整
        //  每次都把跟节点跟最后一个节点去交换. 交换完,数组长度-1, 并且重新排列一次
        int temp = 0 ; // 临时变量
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 现在跟节点已经不是最大了, 所以我们需要排列一次
            adjustHeap(arr, i, 0);
        }

        // 遍历完就已经可以输出了
        System.out.println(Arrays.toString(arr));


    }

    /**
     * 把数组排序成一个符合要求的大顶堆
     * @param arr    数组
     * @param length 最大数组长度
     * @param i      表示非叶子节点
     */
    public static void adjustHeap(int[] arr, int length, int i) {

        int temp = arr[i]; // 暂时记录住这个数
        // 从这个非叶子节点判断他跟他所有的子节点对比, 把大的往上提
        for (int k = i * 2 + 1; k < length; k = i * 2 + 1) {
            // i * 2 + 1 表示当前节点的子节点的位置
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 说明左子节点比右子节点大
                k++;
            }

            // 经过上面已经可以知道是子节点中比较大的那个了
            // arr[k]  现在是比较大的子节点
            if (arr[k] > temp) {
                // 表示子节点有比temp大.
                arr[i] = arr[k];// 把他移动过去
                i = k;  // 在把下标移动到被替换的位置
            } else {
                //  因为我们排列大顶堆是从下往上的,
                //  所以我们遇到小的可以直接跳过, 因为下面的都排列好了
                break;
            }
        }
        // 循环完了就可以吧K的位置变成原来i的数值了
        arr[i] = temp;
    }
}
