package com.ty.recursion;

/**
 * program : OneCode
 * description : 递归演示
 * author : jyf
 * date : 2020-07-29 11:09
 **/
public class RecursionTest {

    public static void main(String[] args) {
        // 通过打印问题,回顾递归调用机制
        int result = factorial(4);
        System.out.println("result=" + result);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println("n=" + n);
        }
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n; // 1 * 2 * 3
        }
    }
}
