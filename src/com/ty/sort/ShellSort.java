package com.ty.sort;

import java.util.Arrays;

/**
 * program : OneCode
 * description : 希尔排序
 * author : jyf
 * date : 2020-08-04 11:40
 **/
public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);

        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
//        shellSort(arr);// 交换法 4416 4.4S
        shellSort2(arr); // 位移法-> 14 0.1S
        long end = System.currentTimeMillis();

        System.out.println("希尔排序耗时");
        System.out.println(end - start); // 14 0.1S
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        // 希尔排序就是根据数组长度以此除以2 分组,然后把每个组小的数 往前放
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 这里表示从哪开始
            for (int i = gap; i < arr.length; i++) {
                // 进行交换
                // 这样做的好处可以每次都把最小的送到最前面去
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        /*
        // 希尔排序就是根据数组长度以此除以2 分组,然后把每个组小的数 往前放
        int shellVal = 5;
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            // 进行交换
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j+5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }

        System.out.println("第一次希尔排序以后");
        System.out.println(Arrays.toString(arr));

        for (int i = 2; i < arr.length; i++) {
            // 进行交换
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j+2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }

        System.out.println("第二次希尔排序以后");
        System.out.println(Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            // 进行交换
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        System.out.println("第三次希尔排序以后");
        System.out.println(Arrays.toString(arr));

*/
    }

    // 我们对希尔排序进行优化位移法 -> 插入法
    public static void shellSort2(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 把位置放到最前面
                int j = i;
                int temp = arr[j];
                if (temp < arr[j-gap]){ // 表示temp比较小
                    // 进来的话继续往前比较,看有没有比temp更小的
                    while (j-gap >= 0 && temp < arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }

    }

}
