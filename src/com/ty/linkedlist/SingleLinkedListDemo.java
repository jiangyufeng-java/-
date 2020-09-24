package com.ty.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * program : DataStructures
 * description :
 * author : jyf
 * date : 2020-06-28 15:22
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "吕俊逸", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5 ,"小花", "花花");
        HeroNode heroNode6 = new HeroNode(6, "小白", "白白");

        // 创建加入链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(heroNode5);
        singleLinkedList1.addByOrder(heroNode6);
//        // 加入
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);
        // 加入
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.list();
//        SingleLinkedList.join(singleLinkedList.getHead(),singleLinkedList1.getHead());
        // singleLinkedList.update(heroNode4);
        // 显示
//        singleLinkedList.list();
//        HeroNode heroNode5 = new HeroNode(3, "有用", "智多星");
//        singleLinkedList.update(heroNode5);
        System.out.println("---------------------------");
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(3);
//        singleLinkedList.delete(4);
        SingleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
//        SingleLinkedList.reversePrint(singleLinkedList.getHead());

//        int length = SingleLinkedList.getLength(singleLinkedList.getHead());
//        System.out.println("长度为: " + length);
//        HeroNode lastIndexNode = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 100);
//        System.out.println(lastIndexNode);
    }
}

// 定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {

    // 先初始化一个头结点 头结点不要动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 合并两个有序单链表,合并之后的链表依然有序
    public static void join(HeroNode head1,HeroNode head2){
        HeroNode next1 = head1.next;
        HeroNode next2 = head2.next;
        HeroNode result = new HeroNode(0, "","");
        if (next1 == null && next2 != null){
            result.next = next2;
        }else if (next2 == null && next1 != null){
            result.next = next1;
        }
        HeroNode resultTemp = result;
        while (next1 != null || next2 != null){
            if ( next1 == null ){
                resultTemp.next = next2;
                resultTemp = resultTemp.next;
                break;
            }else if (next2 == null){
                resultTemp.next = next1;
                resultTemp = resultTemp.next;
                break;
            }

            if (next1.no >= next2.no){
                resultTemp.next = next2;
                next2 = next2.next;
            }else{
                resultTemp.next = next1;
                next1 = next1.next;
            }
            resultTemp = resultTemp.next;
        }
        SingleLinkedList list = new SingleLinkedList();
        HeroNode head = list.getHead();
        head.next = result.next;
        list.list();
    }

    // 从尾到头打印单链表
    // 模拟栈,先进后出,压栈
    public static void reversePrint(HeroNode head){
        // 先判断是否为空
        if (head.next == null || head.next.next == null){
            return;
        }

        // 定义一个辅助变量, 拿出来一个栈进去一个
        Stack<HeroNode> stack = new Stack<>();
        HeroNode heroNode = head.next;
        while (heroNode != null){
            stack.push(heroNode);
            heroNode = heroNode.next;
        }
        // 完毕压栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    // 单链表反转
    public static void reverseList(HeroNode head) {
        // 新的头结点
        HeroNode newHeroNode = new HeroNode(0, "", "");
        // 遍历旧的节点
        HeroNode next = head.next;
        HeroNode temp = null;
        while (true) {
            if (next == null) {
                break;
            }
            // 临时存储下一个节点
            temp = next.next;
            // 修改下一个节点的信息.
            next.next = newHeroNode.next;
            // 把新节点指向 next
            newHeroNode.next = next;
            next = temp;
        }
        head.next = newHeroNode.next;
    }

    // 查找链表中的倒数第K个结点[新浪面试题]
    // 思路
    // 1.编写一个方法,接受head节点,同时接受一个index
    // 2.index 表示是倒数第index个节点
    // 3.先把链表从头到尾遍历,得到这个链表总的长度
    // 4.得到size后,我们从链表的第一个开始遍历(size-index)个,就可以得到
    // 找到返回, 找不到返回null
    public static HeroNode findLastIndexNode(HeroNode heroNode, int index) {
        int length = getLength(heroNode);
        HeroNode temp = heroNode.next;
        int i1 = length - index;
        if (i1 < 0 || index < 0) {
            return null;
        }
        for (int i = 0; i < i1; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 添加节点到单向链表
    // 思路,当不考虑编号顺序时
    // 1.找到当前链表的最后节点
    // 2.将找到这个节点的next 指向新的节点
    public void add(HeroNode headNode) {

        // 因为head节点不能动,因此我们需要一个辅助遍历 temp;
        HeroNode temp = head;
        // 遍历链表,找到最后
        while (true) {
            // 找到链表的最后,就跳出
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后, temp后移
            temp = temp.next;
        }
        // 退出循环的时候,temp就指向了链表的最后
        temp.next = headNode;
    }

    // 修改节点
    public void update(HeroNode heroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~不能更新");
            return;
        }

        // 对链表进行遍历
        HeroNode temp = head.next;
        boolean flag = false; // 添加的编号是否存在, 默认为false
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 表示找到了
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有找到编号为:" + heroNode.no + " 的节点");
        }

    }

    // 删除节点
    //思路
    //1.head 不能动,因此我们需要一个temp辅助节点找到待删除的前一个节点
    // 2.说明我们在比较时,是temp.next.no和需要删除节点的no比较
    public void delete(int no) {

        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有你要找的节点");
                return;
            }
            if (temp.next.no == no) {
                break;
            }
            temp = temp.next;
        }
        // 出来表示找到了该节点
        temp.next = temp.next.next;
    }

    // 方法:获取到单链表的节点的个数(如果是带头结点的链表,需要不统计头结点)
    /**
     * @param heroNode 链表的头结点
     * @return 返回的是有效节点的个数
     */
    public static int getLength(HeroNode heroNode) {
        HeroNode next = heroNode.next;
        int count = 0;
        // 直接 循环直到next.next 不为空
        while (true) {
            if (next == null) {
                break;
            }
            count++;
            next = next.next;
        }
        return count;
    }

    // 第二种方式在添加英雄时,根据排名将英雄插入到指定位置
    // (如果有这个排名添加失败,给出提示)
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动,因此我们任然通过一个辅助指针(变量)来帮助找到添加的位置
        // 因为单链表, 因为我们找的temp 是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 添加的编号是否存在, 默认为false
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后了
                break; // 结束
            }
            if (temp.next.no > heroNode.no) {  // 下一个节点比temp大,那肯定是添加到当前节点后面
                break;
            } else if (temp.next.no == heroNode.no) {
                // 表示希望添加的HeroNode的编号已然存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag 值
        if (flag) {
            // 表示存在了
            System.out.printf("准备插入的英雄的编号 %d 已经存在了,不能加入 \n", heroNode.no);
        } else {
            // 插入到链表中,temp
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动, 因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            // 输出节点信息
            System.out.println(temp);
            // temp后移
            temp = temp.next;
        }
    }
}

// 定义HeroNode ,每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode(int hNo, String hName, String hNickname) {
        no = hNo;
        name = hName;
        nickname = hNickname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}