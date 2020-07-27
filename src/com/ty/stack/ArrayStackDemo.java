package com.ty.stack;


import java.util.Scanner;

/**
 * program : OneCode
 * description : 数组实现栈
 * author : jyf
 * date : 2020-07-21 10:06
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 测试ArrayStack 是否正确
        // 创建ArrayStack 对象-> 表示栈
        ArrayStack stack = new ArrayStack(4);
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


// 定义一个ArrayStack 表示栈
class ArrayStack {

    private int maxSize; // 栈大小
    private int[] stack; // 数组,数组模拟栈,数据就被放在数组里
    private int top = -1;// top 表示栈顶,初始化为-1

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈空
    public Boolean isEmpty() {
        return top < 0;
    }

    // 入栈
    public void push(int value) {
        // 判断是否满
        if (isFull()) {
            System.out.println("栈满了!!");
            return;
        }
        // 让栈底+1;
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        // 先取出栈最上方的
        int value = stack[top];
        // 出栈以后栈内只有 top-1
        top--;
        return value;
    }

    // 遍历栈
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

}