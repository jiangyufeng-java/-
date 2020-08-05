package com.ty.sort;

import java.util.Arrays;

/**
 * program : OneCode
 * description : 插入排序
 * author : jyf
 * date : 2020-08-04 10:46
 **/
public class InsertSort {

    public static void main(String[] args) {
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();

        System.out.println("插入排序:");
        System.out.println(end-start); // 527 0.5S
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        // 插入排序思想: 类似于打牌,以第一个为基准,依次把牌放入该放的位置

        for (int i = 1; i < arr.length; i++) {
            // 第一轮
            int insertVal = arr[i];
            int index = i - 1; // 这个表示插入的前一个索引

            while (index >= 0 && insertVal < arr[index]) {
                // 表示insert需要放到前面一个位置
                arr[index + 1] = arr[index];  // 相当于把前面的小数往后面移动了
                index--; // 表示要往前遍历去比较当前这个数
            }

            if (index + 1 != i) {
                arr[index + 1] = insertVal; // 表示都到了合适的位置,最后把这个数放进去, 因为里面--了,所以这里要加1
               // System.out.println("第" + i + "轮排序过后");
               // System.out.println(Arrays.toString(arr));
            }
        }
       /* // 第二轮
        insertVal = arr[2];
        index = 2 - 1; // 这个表示插入的前一个索引

        while (index >= 0 && insertVal < arr[index]){
            // 表示insert需要放到前面一个位置
            arr[index + 1] = arr[index];  // 相当于把前面的小数往后面移动了
            index--; // 表示要往前遍历去比较当前这个数
        }

        arr[index + 1] = insertVal; // 表示都到了合适的位置,最后把这个数放进去, 因为里面--了,所以这里要加1
        System.out.println("第二轮排序过后");
        System.out.println(Arrays.toString(arr));

        // 第三轮
        insertVal = arr[3];
        index = 3 - 1; // 这个表示插入的前一个索引

        while (index >= 0 && insertVal < arr[index]){
            // 表示insert需要放到前面一个位置
            arr[index + 1] = arr[index];  // 相当于把前面的小数往后面移动了
            index--; // 表示要往前遍历去比较当前这个数
        }

        arr[index + 1] = insertVal; // 表示都到了合适的位置,最后把这个数放进去, 因为里面--了,所以这里要加1
        System.out.println("第三轮排序过后");
        System.out.println(Arrays.toString(arr));*/


    }
}


