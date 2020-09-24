package com.ty.recursion;

import java.util.Arrays;

/**
 * program : DataStructures
 * description : 8皇后算法
 * author : jyf
 * date : 2020-07-30 15:05
 **/
public class Queue8 {

    // 表示最大是8
    int max = 8;

    static int[] array = new int[8];
    static int count= 0;

    public static void main(String[] args) {
        //测试一把 ， 8 皇后是否正确
        Queue8 queue8 = new Queue8();
       /* for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }*/
        queue8.check(1);
        System.out.printf("一共有%d 解法", count);
//        System.out.printf("一共判断冲突的次数%d 次", judgeCount); // 1.5w
    }

    // 写一个方法开始放皇后
    private void check(int n) {
        if (n == 9) {
            // 表示为8了就不能放了, 皇后是从0开始放的
            print();
            return;
        }

        for (int i = 1; i <= 8; i++) {
            array[n-1] = i;
            // 遍历去摆放皇后
            if (judge(n)) {
                //如果这个位置OK  那么直接摆放下一个皇后
                check(n + 1);
            }

            //如果最后回溯回来,这个位置不OK 那么皇后的位置继续往后移动一个,直到把所有的位置都试出来.

        }
    }

    //查看当我们放置第 n 个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 当前放置的是第几个皇后
     * @return
     */
    private boolean judge(int n) {
        // 因为皇后都是从0开始的
        for (int i = 1; i < n; i++) {
            // array[i-1] == array[n-1] 判断是否这个位置之前有同位置的了
            //Math.abs(n - i) == Math.abs(array[n-1] - array[i-1]) 求斜率
            // Math.abs(n - i)  得出当前皇后和i这个皇后相差多少个数
            // Math.abs(array[n-1] - array[i-1]) 相当于他们的值相差多少个数
            // 因为棋盘是8*8 的所以如果两个位置都是等差, 那么他们就是在同一个斜角上
            if (array[i-1] == array[n-1] || Math.abs(n - i) == Math.abs(array[n-1] - array[i-1])) {
                return false;
            }
        }
        return true;
    }


    // 遍历当前数组
    private void print() {
        count++;
        System.out.println(Arrays.toString(array));
    }


}
