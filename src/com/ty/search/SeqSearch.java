package com.ty.search;

/**
 * program : OneCode
 * description : 线性查找
 * author : jyf
 * date : 2020-08-08 14:57
 **/
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = { 1, 9, 11, -1, 34, 89 };// 没有顺序的数组
        int index = seqSearch(arr, 34);
        if (index >= 0 ){
            System.out.println("找到了在索引"+ index + "上");
        }else{
            System.out.println("没有找到这个数");
        }

    }

    /**
     * 线性查找, 找到一个数即返回他的索引
     * @param arr  数组
     * @param value  需要找的数
     * @return
     */
    public static int seqSearch(int[] arr , int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
