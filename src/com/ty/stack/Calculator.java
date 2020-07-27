package com.ty.stack;

/**
 * program : OneCode
 * description : 10 位一下简单计算器Link
 * author : jyf
 * date : 2020-07-22 16:54
 **/
public class Calculator {

    public static void main(String[] args) {

        // 一个数栈, 一个符号栈
        // 7*2*2-5+1-5+3-3 = 19
        String expression = "50-2*30+100"; //计算式
        ArrayStack2 numStack = new ArrayStack2(10);  // 数字栈
        ArrayStack2 operStack = new ArrayStack2(10); // 符号栈
        int num1 = 0;
        int num2 = 0;
        char oper = ' ';
        int result = 0;
        // 转成字符数组
        String temp = "";
        boolean flag = false;
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            oper = chars[i];
            if (operStack.isOper(oper)) {
                // 表示为字符
                // 如果符号栈为空,直接入栈即可
                if (operStack.isEmpty()) {
                    // 为空 直接入栈
                    operStack.push(oper);
                } else {
                    // 有操作符
                    //如果当前的操作符的优先等级小于或等于当前栈顶的操作符
                    if (operStack.priority(oper) <= operStack.priority(operStack.cheek())) {
                        //那就pop出两个数字,和操作符进行运算,运算以后,再把结果放进去,
                        // 随后放入当前操作符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        result = numStack.cal(num1, num2, operStack.pop());
                        //放入计算结果
                        numStack.push(result);
                        //放入操作符前判断, 是否其中还有操作符
                        if (operStack.getTop() != -1 && operStack.priority(oper) <= operStack.priority(operStack.cheek())) {
                            // 有的话在走一遍循环
                            i--;
                        } else {
                            // 如果没有
                            operStack.push(oper);
                        }

                    } else {
                        // 如果当前操作符的优先级,大于栈中的操作符 直接入栈
                        operStack.push(oper);
                    }

                }
            } else {
                // 表示为数字
                temp = temp + oper;
                // 判断是否到最后了
                if (i + 1 < chars.length) {
                    // 说明没到最后
                    // 判断下一个是否是符号 如果是符号那么直接解析放入栈,  如果不是直接下一个循环进行判断.
                    if (numStack.isOper(chars[i + 1])) {
                        // 是符号
                        numStack.push(Integer.parseInt(temp));
                        temp = "";
                    }
                } else {
                    // 如果到最后了直接添加
                    numStack.push(Integer.parseInt(temp));
                }
            }
        }

        // 上面放完了,现在开始计算了
        // 会出现6-2*3+1=-1 从头开始计算保证结果
        while (true) {
            if (numStack.getTop() == 0) {
                // 说明已经运算完了
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            result = numStack.cal(num1, num2, operStack.pop());
            //放入计算结果
            numStack.push(result);
        }

        // 从头开始遍历保证计算结果
        /*int count = 0;
        for (int i = 0; i < numStack.getTop(); i++) {
            num1 = numStack.stack[i];
            num2 = numStack.stack[i+1];
            result = numStack.cal(num2, num1, operStack.stack[i]);
            count++;
            numStack.stack[0] = result;
            numStack.stack[i+1] = result;
        }

        for (int i = 0; i < count; i++) {
            numStack.pop();
        }*/
        // 计算完毕
        System.out.println(expression + "=" + numStack.pop());

    }
}


// 定义一个ArrayStack 表示栈
class ArrayStack2 {

    private int maxSize; // 栈大小
    public int[] stack; // 数组,数组模拟栈,数据就被放在数组里
    private int top = -1;// top 表示栈顶,初始化为-1

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    // 构造器
    public ArrayStack2(int maxSize) {
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

    // 判断是否是字符
    public boolean isOper(int oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    // 返回是字符的优先级
    public int priority(int oper) {
        return oper == '*' || oper == '/' ? 1 : 0;
    }

    // 看一眼当前栈顶的值
    public int cheek() {
        return stack[top];
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

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}