package com.ty.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * program : DataStructures
 * description : 链表实现栈
 * author : jyf
 * date : 2020-07-21 10:39
 **/
public class LinkedStackDemo {

    public static void main(String[] args) {
// 测试ArrayStack 是否正确
        // 创建ArrayStack 对象-> 表示栈
        LinkedStack stack = new LinkedStack();
        String key = "";
        boolean loop = true; // 控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;

            }
        }
        System.out.println("程序退出~~~");

    }
}

class LinkedStack {
    private MyStack stack = new MyStack(null);

    // 入栈
    public void push(int no) {
        // 创建新的栈
        MyStack newStack = new MyStack(no);
        // 头插法插入
        newStack.nextStack = stack;
        this.stack = newStack;
    }

    // 出栈
    public Integer pop() {
        if (isEmpty()) {
            throw new RuntimeException("当前栈为空");

        }
        Integer value = stack.stack;
        stack = stack.nextStack;
        return value;
    }

    // 判断为空
    public boolean isEmpty() {
        return stack.stack == null;
    }

    // 展示
    public void show() {
        if (isEmpty()) {
            System.out.println("当前栈为空");
            return;
        }
        MyStack temp = stack;
        while (true) {
            if (temp.stack == null) {
                break;
            }
            System.out.print(temp.stack + " ");
            temp = temp.nextStack;
        }
        System.out.println();
    }
}

class MyStack {

    public Integer stack;  // 当前栈
    public MyStack nextStack; // 下一个栈

    public MyStack(Integer no) {
        this.stack = no;
    }

    @Override
    public String toString() {
        return "LinkedStack{" +
                "stack=" + stack +
                '}';
    }
}
