package com.ty.hashtab;

import java.util.Scanner;

/**
 * program : OneCode
 * description : 用哈希做缓存
 * author : jyf
 * date : 2020-08-11 14:15
 **/
public class HashTabDemo {

    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要删除的 id");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}


// 定义一个hash表
class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        // 初始化哈希表
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    // 添加员工
    public void add(Emp e) {
        int hashNo = getHashNo(e.no);
        empLinkedLists[hashNo].add(e);
    }

    // 展示员工表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }
    }

    // 删除一个员工
    public void delete(int no) {
        int hashNo = getHashNo(no);
        empLinkedLists[hashNo].delete(no);
    }

    // 获取哈希值 自定义的算法
    public int getHashNo(int no) {
        return no % size;
    }


}


// 定义链表
class EmpLinkedList {
    // 默认为空
    public Emp head;

    // 添加一个员工
    public void add(Emp emp) {
        // 判断头节点是否为空
        if (head == null) {
            head = emp;
        } else {
            Emp e = head;
            while (true) {
                if (e.next == null) {
                    // 说明到了最后一个节点
                    e.next = emp;
                    break;
                }
                e = e.next;
            }
        }
    }

    //遍历链表的雇员信息
    public void list() {
        if (head == null) {
            System.out.println("当前链表为空");
        } else {
            Emp e = head;
            while (true) {
                System.out.println("员工编号为: " + e.no + " 姓名为: " + e.name);
                if (e.next == null) {
                    // 说明到了最后一个节点了
                    break;
                }
                e = e.next;
            }
        }
    }

    // 删除一个节点信息
    public void delete(int no) {
        if (head == null) {
            System.out.println("没有这个员工");
            return;
        } else {
            // 说明不为空去寻找
            if (head.no == no){
                // 说明头结点就是这个
                head = null;
            }else {
                Emp e = head;
                while (true) {

                    if (e.next == null) {
                        System.out.println("没有找到该员工");
                        break;
                    }
                    if (e.next.no == no) {
                        // 说明找到下一个节点就是该员工
                        e.next = e.next.next;
                        System.out.println("删除成功");
                        break;
                    }
                    // 指针往后移动
                    e = e.next;
                }
            }
        }
    }
}


// 定义员工信息
class Emp {
    public int no;
    public String name;
    public Emp next;

    public Emp(int no, String name) {
        this.no = no;
        this.name = name;
    }
}
