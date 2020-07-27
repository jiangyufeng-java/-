package com.ty.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * program : OneCode
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

    }
    /**
     * 将一个中缀表达式转成一个后缀表达式
     */
    public static List<String> toInfixExpressionList(String s){
        return null;
    }


    /**
     * 转成一个list集合
     * @param suffixExpression
     * @return
     */
    public static List<String> getList(String suffixExpression){
        String[] strings = suffixExpression.split(" ");
        List<String> list = Arrays.asList(strings);
        return list;
    }

    /**
     * 计算后缀表达式
     * @param list
     * @return
     */
    public static int calculate(List<String> list){
        // 我们只需要一个栈存放数字即可
        Stack<Integer> stack = new Stack<>();
        list.stream().forEach(s -> {
            if (s.matches("\\d+")){
                // 表示进来为数字 直接入栈即可
                stack.push(Integer.parseInt(s));
            }else{
                // 表示为符号
                // 那我们弹出两个数字和当前运算符来进行计算
                int num2 = stack.pop();
                int num1 = stack.pop(); // 需要在前面的数第二个弹出来
                int result = 0;
                switch (s){
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "×":
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
