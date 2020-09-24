package com.ty.search;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * program : DataStructures
 * description : 斐波那契查找算法
 * author : jyf
 * date : 2020-08-10 16:31
 **/
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(fib()));
        int[] arr = {1,8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 1000);
        System.out.println(index);

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
     * 斐波那契查找法
     *
     * @param arr   数组
     * @param value 需要找的值
     * @return
     */
    public static int fibonacciSearch(int[] arr, int value) {
        int[] fib = fib(); // 斐波那契数列
        int fibIndex = 0;  // 斐波那契的下标
        int left = 0;  // 左下标
        int right = arr.length - 1;  // 右下标

        // 因为right使用的是索引, 从0开始. 所以fib[fibIndex] - 1 把元素个数变成索引
        while (right > fib[fibIndex] - 1) {
            fibIndex++;
        }
        // 遍历完之后就获得了一个斐波那契数列的数号
        int[] fibs = Arrays.copyOf(arr, fib[fibIndex]);  // 这是新的一个数组, 因为后面填充的都是0 0 0
        // 所以我们需要遍历这个数组把后面的0 变成arr数组的最后一个数
        for (int i = right + 1; i < fibs.length; i++) {
            fibs[i] = arr[right];
        }

        // 接下来可以进行比较了
        while (left <= right) {
            // 黄金分割点的索引位置
            // 因为这里是左边+上斐波那契数列的黄金分割点  也就是开始位置, 加上当前斐波那契数列的点
            int mid = left + fib[fibIndex - 1] - 1;
            if (value > fibs[mid]) {
                left = mid + 1;
                // 说明这个数比mid大,那我们需要往右边去找
                fibIndex -= 2;
            }else if (value < fibs[mid]){
                // 因为是往左边去找
                // 修改左边的最大值
                right = mid - 1;
                // 这里-- 是因为 fibs[fibIndex] = fibs[fibIndex - 1] + fibs[fibIndex - 2]
                // 因为是小于所以是在左边, 也就是大的那边
                fibIndex --;
            }else {
                // 说明找到了
                // 确认需要返回的下标
                if (mid <= right){
                    return mid;
                }else {
                    return right;
                }

            }
        }
        return -1;
    }
}
