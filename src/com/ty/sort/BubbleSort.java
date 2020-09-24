package com.ty.sort;

import java.util.Arrays;

/**
 * program : DataStructures
 * description : 冒泡排序
 * author : jyf
 * date : 2020-08-03 14:20
 **/
public class  BubbleSort {

    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
//        int arr[] = {3, 9, -1, 10, 20};
//        System.out.println("原始数组");
//        System.out.println(Arrays.toString(arr));
        // 进行性能测试
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*arr.length);
        }
        long start = System.currentTimeMillis();
        bubble(arr);
        long end = System.currentTimeMillis();

//        System.out.println(Arrays.toString(arr));
        System.out.println(end-start); // 9129 9S



/*
        // 第二次排序
        for (int j = 0; j < arr.length - 1 -1; j++) {
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第二次排序以后");
        System.out.println(Arrays.toString(arr));

        for (int j = 0; j < arr.length - 1 - 2; j++) {
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第三次排序以后");
        System.out.println(Arrays.toString(arr));

        for (int j = 0; j < arr.length - 1 - 3; j++) {
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }

        System.out.println("第四次排序以后");
        System.out.println(Arrays.toString(arr));
        */
    }

    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubble(int[] arr) {
        //冒泡排序
        int temp = 0; // 临时变量
        boolean flag = false; // 标识符,表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            // 需要循环数组长度-1次
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 每次循环一次都能确定尾数是最大的.所以我们每次循环的次数都慢慢减少
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    // 如果当前的数比后面的数大,就交换位置
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第"+(i+1)+"次排序以后");
//            System.out.println(Arrays.toString(arr));
            if (!flag){
                break;
            }else{
                flag = false;
            }
        }
    }
}
