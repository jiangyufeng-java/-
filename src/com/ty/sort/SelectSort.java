package com.ty.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * program : OneCode
 * description : 选择排序
 * author : jyf
 * date : 2020-08-03 17:04
 **/
public class SelectSort {

    public static void main(String[] args) {
       // int[] arr = {101, 119, 34, 1, -1, 99, 86};


        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*arr.length);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();

        System.out.println("选择排序耗时");
        System.out.println(end-start); // 1795 1.8S
    }

    public static void selectSort(int arr[]) {

        // 选择排序就是每一次把最小的放到第一个位置
        // 假定最小的是第一个数,也就是0索引

        // 因为0索引已经被我们假定了,直接从1开始
        // 同样的道理 遍历次数是数组长度-1
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            int min = arr[index];
            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]) {
                    // 表示min 不是最小数
                    index = j;
                    min = arr[j];
                }
            }
            // 循环完了就可以吧第一个数的值改成最小值
            // 先把我们知道的最小值索引的数更改成0索引位置的数
            // 添加一个优化判断
            if (index != i) {
                arr[index] = arr[i];
                // 修改0的值
                arr[i] = min;
            }
//            System.out.println("第一轮循环以后的数组");
//            System.out.println(Arrays.toString(arr));


        }

       /* // 第二轮循环
        index = 1;
        min = arr[index];
        for (int i = 1 + 1; i < arr.length; i++) {
            if (min > arr[i]){
                // 表示min 不是最小数
                index = i;
                min = arr[i];
            }
        }
        // 循环完了就可以吧第一个数的值改成最小值
        // 先把我们知道的最小值索引的数更改成0索引位置的数
        arr[index] = arr[1];
        // 修改0的值
        arr[1] = min;
        System.out.println("第二轮循环以后的数组");
        System.out.println(Arrays.toString(arr));

        // 第三轮循环
        index = 2;
        min = arr[index];
        for (int i = 1 + 2; i < arr.length; i++) {
            if (min > arr[i]){
                // 表示min 不是最小数
                index = i;
                min = arr[i];
            }
        }
        // 循环完了就可以吧第一个数的值改成最小值
        // 先把我们知道的最小值索引的数更改成0索引位置的数
        arr[index] = arr[2];
        // 修改0的值
        arr[2] = min;
        System.out.println("第三轮循环以后的数组");
        System.out.println(Arrays.toString(arr));*/
    }
}
