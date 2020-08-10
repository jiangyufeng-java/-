package com.ty.search;

import java.util.ArrayList;
import java.util.List;

/**
 * program : OneCode
 * description : 二分查找
 * author : jyf
 * date : 2020-08-10 09:19
 **/
public class BinarySearch {

    public static void main(String[] args) {
//        int[] arr =  {1,8, 10, 89, 1000, 1234};
//        int index = binarySearch(arr, 0, arr.length - 1, -1);
//        System.out.println(index);

        int[] arr =  {1,8, 10, 89, 1000, 1000, 1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000, 1234};
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(integers);

    }

    /**
     * 二分查找算法
     * 前提是这个数组是有序的
     * @param arr  数组
     * @param left  左边开始索引
     * @param right  右边结束索引
     * @param value  需要找的值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        // 首先需要确定left小于right说明找完了也没找到
        if (left > right){
            return -1;
        }

        // 获得中间索引
        int mid = (left + right) / 2;
        // 获得中间索引对应的值
        int midValue = arr[mid];

        if (value > midValue) {
            // 说明要向右边找
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            // 说明向左寻找
            return binarySearch(arr, left, mid - 1, value);
        }else{
            // 说明是等于 也就是找到了
            return mid;
        }
    }

    /**
     * 这个数组有多个同样的数,我们都把他找到
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        // 首先需要确定left小于right说明找完了也没找到
        if (left > right){
            return new ArrayList<>();
        }

        // 获得中间索引
        int mid = (left + right) / 2;
        // 获得中间索引对应的值
        int midValue = arr[mid];

        if (value > midValue) {
            // 说明要向右边找
            return binarySearch2(arr, mid + 1, right, value);
        } else if (value < midValue) {
            // 说明向左寻找
            return binarySearch2(arr, left, mid - 1, value);
        }else{
            // 说明是等于 也就是找到了
            // 因为可能还会有别的数学,所以我们还需要去前面和后面都看看
            int  temp = mid - 1;
            List<Integer> indexList = new ArrayList<>();
            // 向左边找
            while (true){
                if (temp < 0 ||  arr[temp] != midValue){
                    break;
                }
                indexList.add(temp);
                temp--;
            }
            // 找完左边再把当前这个mid+进去, 保证看起来是有序的
            indexList.add(mid);

            temp = mid + 1;
            // 向右边去查找
            while (true){
                if (temp >= arr.length || arr[temp] != midValue){
                    break;
                }
                indexList.add(temp);
                temp++;
            }
            return indexList;
        }
    }
}
