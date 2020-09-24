package com.ty.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * program : DataStructures
 * description : 逆波兰表达式
 * author : jyf
 * date : 2020-07-27 11:28
 **/
public class PolandNotation {
    public static void main(String[] args) {

        // 完成中缀表达式转成 后缀表达式的做法
        // 1+((2+3)×4)-5 => 1 2 3 + 4 × + 5 –
        // 1. 直接对字符串进行操作,不方便, 因此先将 " 1+((2+3)×4)-5 中缀的表达式对应的list
        // 即 "1+((2+3)×4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        // 2 将list集合转成后缀表达式
        List<String> expressionList = parseSuffixExpressionList(list);
        // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  ==>  ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.println("后缀表达式为: " + expressionList);
        // 计算后缀表达式
        System.out.println("计算结果为: " + calculate(expressionList));

       /*
        // 先定义给逆波兰表达式
//        String suffixExpression = "30 4 + 5 × 6 - ";
        // 4 5 * 8 - 60 + 8 2 / +
        String suffixExpression = "4 5 × 8 - 60 + 8 2 / +";
        // 首先进行转换成一个字符串数组
        List<String> list = getList(suffixExpression);
        System.out.println(list);
        // 随后定义一个方法获取逆波兰表达式计算的值
        int calculate = calculate(list);
        System.out.println("运算结果为: " + calculate);
        */
    }

    // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  ==>  ArrayList [1,2,3,+,4,*,+,5,–]

    /**
     * 方法:得到中缀表达式转为对应的后缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 初始化两个栈
        Stack<String> symbol = new Stack<>();
        // 因为第二个栈只需要添加不需要pop 所以我们可以用集合代替
        List<String> list = new ArrayList<>();
        // 遍历集合来转换
        ls.forEach(s -> {
            if (s.matches("\\d+")) {
                // 说明是个数字
                list.add(s);
            }
            // 说明是个符号
            else if ("(".equals(s)) {
                // 说明是个左括号,直接压入栈
                // 如果是左括号“(”，则直接压入 s1
                symbol.push(s);
            } else if (")".equals(s)) {
                // 如果是右括号, 那么就把之前的符号全部弹出到第二个栈中
                while (symbol.size() != 0 && !"(".equals(symbol.peek())) {
                    list.add(symbol.pop());
                }
                symbol.pop();
            } else { // 遇到运算符时，比较其与 s1 栈顶运算符的优先级
                // 目前需要一个比较方法
                //当 s 的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1)
                // 与 s1 中新的栈顶运算符相比较
                while (symbol.size() != 0 && priority(s) <= priority(symbol.peek())) {
                    // 将 s1 栈顶的运算符弹出并加入到 s2 中
                    list.add(symbol.pop());
                }
                // 完成以后就把它压入栈中
                symbol.push(s);
            }

        });

        while (symbol.size() != 0) {
            list.add(symbol.pop());
        }
        return list;
    }

    /**
     * 判断字符的优先级
     */
    public static int priority(String oper) {
        if (oper.equals("*") || oper.equals("/")){
            return 1;
        }else if (oper.equals("+") || oper.equals("-")){
            return 0;
        }
        return  -1;
    }


    /**
     * 将一个中缀表达式转成list集合
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>();
        // 转成字符数组
        char[] chars = s.toCharArray();
        String temp = "";
        // 遍历
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // 判断是字符还是数字
            if (c < '0' || c > '9') {
                // 表示他是一个字符
                list.add(String.valueOf(c));
            } else {
                // 不是字符
                temp = "";
                // 先拼接当前的
                temp = temp + c;
                // 判断下一个是否是字符
                while (i + 1 < chars.length && String.valueOf(chars[i + 1]).matches("\\d+")) {
                    i++;
                    temp = temp + chars[i + 1];
                }
                list.add(temp);
            }
        }
        return list;
    }


    /**
     * 转成一个list集合
     *
     * @param suffixExpression
     * @return
     */
    public static List<String> getList(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        List<String> list = Arrays.asList(strings);
        return list;
    }

    /**
     * 计算后缀表达式
     *
     * @param list
     * @return
     */
    public static int calculate(List<String> list) {
        // 我们只需要一个栈存放数字即可
        Stack<Integer> stack = new Stack<>();
        list.stream().forEach(s -> {
            if (s.matches("\\d+")) {
                // 表示进来为数字 直接入栈即可
                stack.push(Integer.parseInt(s));
            } else {
                // 表示为符号
                // 那我们弹出两个数字和当前运算符来进行计算
                int num2 = stack.pop();
                int num1 = stack.pop(); // 需要在前面的数第二个弹出来
                int result = 0;
                switch (s) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符错误");
                }
                stack.push(result);
            }
        });

        return stack.pop();
    }
}
