package com.ty.search;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * program : OneCode
 * description : 斐波那契查找算法
 * author : jyf
 * date : 2020-08-10 16:31
 **/
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
    }

    /**
     * 获取一个斐波那契数组
     *
     * @return
     */
    public static int[] fib() {
        // 创建一个斐波那契数列
        int[] arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    /**
     *
     * @param arr
     * @param value
     * @return
     */
    public static int fibonacciSearch(int[] arr, int value){
        int[] fib = fib(); // 斐波那契数列
        int fibIndex = 0;  // 斐波那契的下标
        int left = 0;  // 左下标
        int right = arr.length -1 ;  // 右下标

        // 因为right使用的是索引, 从0开始. 所以fib[fibIndex] - 1 把元素个数变成索引
        while (right > fib[fibIndex] - 1 ){
            fibIndex++;
        }
        // 遍历完之后就获得了一个斐波那契数列的数号
        int[] fibs = Arrays.copyOf(arr, fib[fibIndex]);  // 这是新的一个数组, 因为后面填充的都是0 0 0
        // 所以我们需要遍历这个数组把后面的0 变成arr数组的最后一个数
        for (int i = right + 1; i < fibs.length; i++) {
            fibs[i] = arr[right];
        }

        // 黄金分割点
        int mid = left + fibs[right-1]-1;
        // 接下来可以进行比较了
        while (left < right){
        }
        return -1;
    }
}
