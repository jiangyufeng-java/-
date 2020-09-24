package com.ty.linkedlist;

/**
 * program : DataStructures
 * description :
 * author : jyf
 * date : 2020-07-03 14:50
 **/
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "吕俊逸", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 heroNode5 = new HeroNode2(5 ,"小花", "花花");
        HeroNode2 heroNode6 = new HeroNode2(6, "小白", "白白");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.list();
        System.out.println("----------------------------");
        // 修改
//        HeroNode2 newHeroNode = new HeroNode2(4, "公孙离", "阿狸");
//        doubleLinkedList.update(newHeroNode);
//        doubleLinkedList.list();
//        System.out.println("----------------------------");
//
//        // 删除
//        doubleLinkedList.delete(3);
//        doubleLinkedList.list();

    }

}

class DoubleLinkedList {
    // 先初始化一个头结点 头结点不要动
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }
    // 第二种方式在添加英雄时,根据排名将英雄插入到指定位置
    // (如果有这个排名添加失败,给出提示)
    public void addByOrder(HeroNode2 heroNode) {
        // 因为头节点不能动,因此我们任然通过一个辅助指针(变量)来帮助找到添加的位置
        // 双链表添加
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    // 删除节点
    // 删除节点我们直接找到要删除的节点修改即可
    public void delete(int no) {

        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("没有你要找的节点");
                return;
            }
            if (temp.no == no) {
                break;
            }
            temp = temp.next;
        }
        // 出来表示找到了该节点
        temp.pre.next = temp.next; // 上一个节点指向temp 的下一个
        // 如果是最后一个节点, 下面这行会出现空指针异常
        if (temp.next != null) {
            temp.next.pre = temp.pre;
        }
    }

    // 修改节点
    public void update(HeroNode2 heroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~不能更新");
            return;
        }

        // 对链表进行遍历
        HeroNode2 temp = head.next;
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

    // 添加节点到单向链表
    // 思路,当不考虑编号顺序时
    // 1.找到当前链表的最后节点
    // 2.将找到这个节点的next 指向新的节点
    public void add(HeroNode2 headNode) {

        // 因为head节点不能动,因此我们需要一个辅助遍历 temp;
        HeroNode2 temp = head;
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
        headNode.pre = temp;
    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动, 因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (temp != null) {
            // 输出节点信息
            System.out.println(temp);
            // temp后移
            temp = temp.next;
        }
    }
}

// 定义HeroNode2 ,每个HeroNode 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 pre; // 指向上一个节点

    // 构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
        no = hNo;
        name = hName;
        nickname = hNickname;
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
