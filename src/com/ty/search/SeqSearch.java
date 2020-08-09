package com.ty.search;

/**
 * program : OneCode
 * description : 线性搜索
 * author : jyf
 * date : 2020-08-08 19:09
 **/
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 90, 20, 33, -1};
        int index = search(arr, 0);
        if (index >= 0) {
            System.out.println("找到的索引为:" + index);
        } else {
            System.out.println("没有找到该数");
        }

    }

    public static int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (
                    arr[i] == value
            )
                return i;
        }
        return -1;
    }
}
