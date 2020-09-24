package com.ty.search;

import java.util.Arrays;

/**
 * program : DataStructures
 * description : 插值查找法
 * author : jyf
 * date : 2020-08-10 10:57
 **/
public class InsertValueSearch {
    public static void main(String[] args) {

        // 创建一个1-100的数组
        /*int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/
        int[] arr = {1, 8, 10, 89, 1000, 1234};

//        System.out.println(Arrays.toString(arr));
        int index = insertValueSearch(arr, 0, arr.length - 1, 89);
        System.out.println(index);
    }


    /**
     * 插值查找法, 本质上就是二分查找法的改进.  也就是改进mid 的算法
     *  插值查找法适用于分部比较均匀的数组,  比如 1,2,3,4 这样子的, 如果散布过大不一定好过二分查找法
     * @param arr   数组
     * @param left  左边索引
     * @param right 右边索引
     * @param value 要找的值
     * @return 索引
     */
    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        System.out.println("插值查找法");
        // 首先进行判断优化  因为value 是需要加入到mid的计算中的, 所以value不能太大也不能太小, 保证在合理的范围内
        // arr[left] > value 因为插值查找法 本质上是有序的数组, 所以如果最小的数还大于 value 的话,那么我们就直接返回-1.
        // arr[right] < value 因为插值查找法 本质上是有序的数组, 所以如果最大的数还小于 value 的话,那么我们就直接返回-1.
        if (left > right || arr[left] > value || arr[right] < value) {
            return -1;
        }

        // 跟二分查找法的主要区别就在于这个mid的算法
        // 这个称之为自适应算法
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (value > midValue) {
            // 说明要向右边找
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            // 说明向左寻找
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            // 说明是等于 也就是找到了
            return mid;
        }
    }
}
