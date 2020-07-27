package com.ty.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * program : OneCode
 * description :
 * author : jyf
 * date : 2020-07-15 11:00
 **/
public class Joseph {

    public static void main(String[] args) {
        Boy boy1 = new Boy(1);
        Boy boy2 = new Boy(2);
        Boy boy3 = new Boy(3);
        Boy boy4 = new Boy(4);
        CircleSingLinkedList singLinkedList = new CircleSingLinkedList();
        singLinkedList.addBoy(125);
        singLinkedList.list();
        singLinkedList.countBoy(1, 2, 5);

    }

}

// 创建一个环形的单向链表
class CircleSingLinkedList {
    // 创建一个first节点, 当前没有编号
    private Boy first = new Boy(-1);

    // 添加小孩节点, 构建成一个环形链表
    public void addBoy(int nums) {
        // nums 做一个简单的数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        // 辅助指针
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            // 判断是否等于1
            if (i == 1) {
                first = boy;
                first.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    // 展示节点
    public void list() {
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy);
            if (curBoy.next == first) {
                // 遍历完毕.
                break;
            }
            curBoy = curBoy.next;
        }
    }

    // 根据用户的输入.计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 校验参数
        if (first == null || countNum > nums || startNo < 1) {
            System.out.println("参数不合理");
            return;
        }
        // 首先把辅助指针移动到first前一位
        Boy helper = first;
        while (true) {
            if (helper.next == first) {
                break; // 说明已经到了前面那个节点了
            }
            helper = helper.next;
        }
        // 然后再把指针移动到第几个孩子上面  相当于是 -1
        for (int i = 1; i < startNo; i++) {
            first = first.next;
            helper = helper.next;
        }
        // 现在可以开始解决约瑟夫问题了
        while (true) {
            if (first == helper) {
                // 说明遍历完成了!已经全部出队列了
                break;
            }
            // 注意这里也是 countNum-1
            for (int i = 1; i < countNum; i++) {
                first = first.next;
                helper = helper.next;
            }
            // 数完以后就可以知道现在first的位置是需要出去的.
            System.out.println("出去的小朋友的编号是: " + first.no);
            first = first.next;
            helper.next = first;
        }
        // 如果跳出循环说明已经出的只剩下一个了
        System.out.println("最后出去的小朋友的编号是:" + first.no);
    }
}

// 定义一个节点类
class Boy {

    public int no;// 编号
    public Boy next; // 下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
