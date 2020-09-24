package com.ty.sort;

import java.util.Arrays;

/**
 * program : DataStructures
 * description : 基数排序
 * author : jyf
 * date : 2020-08-07 19:11
 **/
public class BucketSort {

    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};

        int arr[] = new int[80000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
//        bucketSort(arr);
        Arrays.sort(arr);
        long end = System.currentTimeMillis();

        System.out.println("基数排序:");
        System.out.println(end-start); // 527 0.5S

    }

    public static void bucketSort(int[] arr) {
        // 基数排序需要10个桶, 每个桶放的长度就是数组的最大长度
        int[][] bucket = new int[10][arr.length];

        // 此外还需要一个数组来定义我们每个桶存放了的索引
        int[] bucketCounts = new int[10];

        // 首先我们知道循环多少次是根据当前数组最大数来决定的, 所以我们需要找到当前数组最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        // 循环完了我们就找到了最大值 max
        int length = String.valueOf(max).length();
        // 得到了最大长度那就开始循环


        for (int j = 0 ,k = 1; j < length; j++,k*=10) {
        // 第一轮首先除以10求个位数
        for (int i = 0; i < arr.length; i++) {
            //arr[i] % 10 求出来是个位数, 我们的桶是几就对应放在哪,  bucketCounts[arr[i] % 10] 表示当前这个桶的索引
            int n = arr[i]/k % 10;
            bucket[n][bucketCounts[n]] = arr[i];
            // 索引个数向后移动+1;
            bucketCounts[n] += 1;
        }
        // 单独表示索引
        int index = 0;
        // 桶中已经有数了,剩下来我们可以对他进行放回去了
        // 遍历桶中,找到有数的
        for (int i = 0; i < bucketCounts.length; i++) {
            if (bucketCounts[i] != 0) {
                //  他代表了这个桶中有多少数
                for (int i1 = 0; i1 < bucketCounts[i]; i1++) {
                    // 进行索引交换就行了
                    arr[index] = bucket[i][i1];
                    index++;
                }
            }
            bucketCounts[i] = 0;
        }
        }


        /*
        // 第一轮首先除以10求个位数
        for (int i = 0; i < arr.length; i++) {
            //arr[i] % 10 求出来是个位数, 我们的桶是几就对应放在哪,  bucketCounts[arr[i] % 10] 表示当前这个桶的索引
            int n = arr[i] % 10;
            bucket[n][bucketCounts[n]] = arr[i];
            // 索引个数向后移动+1;
            bucketCounts[n] += 1;
        }
        // 单独表示索引
        int index = 0;
        // 桶中已经有数了,剩下来我们可以对他进行放回去了
        // 遍历桶中,找到有数的
        for (int i = 0; i < bucketCounts.length; i++) {
            if (bucketCounts[i] != 0) {
                //  他代表了这个桶中有多少数
                for (int i1 = 0; i1 < bucketCounts[i]; i1++) {
                    // 进行索引交换就行了
                    arr[index] = bucket[i][i1];
                    index++;
                }
            }
            bucketCounts[i] = 0;
        }


        System.out.println("第一轮的数组排序以后" + Arrays.toString(arr));

        // 第二轮首先除以10然后%10求十位数
        for (int i = 0; i < arr.length; i++) {
            //arr[i] % 10 求出来是个位数, 我们的桶是几就对应放在哪,  bucketCounts[arr[i] % 10] 表示当前这个桶的索引
            int n = arr[i] / 10 % 10; // 得到十位数
            bucket[n][bucketCounts[n]] = arr[i];
            // 索引个数向后移动+1;
            bucketCounts[n] += 1;
        }
        // 单独表示索引
        index = 0;
        // 桶中已经有数了,剩下来我们可以对他进行放回去了
        // 遍历桶中,找到有数的
        for (int i = 0; i < bucketCounts.length; i++) {
            if (bucketCounts[i] != 0) {
                //  他代表了这个桶中有多少数
                for (int i1 = 0; i1 < bucketCounts[i]; i1++) {
                    // 进行索引交换就行了
                    arr[index] = bucket[i][i1];
                    index++;
                }
            }
            bucketCounts[i] = 0;
        }

        System.out.println("第二轮的数组排序以后" + Arrays.toString(arr));

        // 第二轮首先除以10然后%10求十位数
        for (int i = 0; i < arr.length; i++) {
            //arr[i] % 10 求出来是个位数, 我们的桶是几就对应放在哪,  bucketCounts[arr[i] % 10] 表示当前这个桶的索引
            int n = arr[i] / 100 % 10; // 得到十位数
            bucket[n][bucketCounts[n]] = arr[i];
            // 索引个数向后移动+1;
            bucketCounts[n] += 1;
        }
        // 单独表示索引
        index = 0;
        // 桶中已经有数了,剩下来我们可以对他进行放回去了
        // 遍历桶中,找到有数的
        for (int i = 0; i < bucketCounts.length; i++) {
            if (bucketCounts[i] != 0) {
                //  他代表了这个桶中有多少数
                for (int i1 = 0; i1 < bucketCounts[i]; i1++) {
                    // 进行索引交换就行了
                    arr[index] = bucket[i][i1];
                    index++;
                }
            }
        }

        System.out.println("第三轮的数组排序以后" + Arrays.toString(arr));
        */


    }


}
