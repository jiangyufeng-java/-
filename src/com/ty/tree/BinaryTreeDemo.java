package com.ty.tree;

/**
 * program : OneCode
 * description : 二叉树
 * author : jyf
 * date : 2020-08-12 11:43
 **/
public class BinaryTreeDemo {

    public static void main(String[] args) {
        // 创建多个节点
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

 /*       System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();*/

        // 前序查找
//        System.out.println("前序查找~~~~");
//        int no = 5;
//        HeroNode heroNode = binaryTree.preSearch(no); // 4
//        if (heroNode == null){
//            System.out.println("没有"+no+"号英雄");
//        }else{
//            System.out.println(no+"号英雄为" + heroNode);
//        }

//        System.out.println("中序查找~~~~");
//        int no = 15;
//        HeroNode heroNode = binaryTree.infixSearch(no);  // 3
//        if (heroNode == null){
//            System.out.println("没有"+no+"号英雄");
//        }else{
//            System.out.println(no+"号英雄为" + heroNode);
//        }

        System.out.println("后序查找~~~~");
        int no = 15;
        HeroNode heroNode = binaryTree.postSearch(no);
        if (heroNode == null){
            System.out.println("没有"+no+"号英雄");
        }else{
            System.out.println(no+"号英雄为" + heroNode);
        }

    }
}


// 表示数
class BinaryTree{

    private HeroNode root; // 表示跟节点
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder(){
        if (root == null){
            System.out.println("当前树为空");
        }else{
            root.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (root == null){
            System.out.println("当前树为空");
        }else{
            root.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder(){
        if (root == null){
            System.out.println("当前树为空");
        }else{
            root.postOrder();
        }
    }

    // 前序查找
    public HeroNode preSearch(int no){
        if (this.root == null){
            System.out.println("当前节点为空");
            return null;
        }else{
           return root.preSearch(no);
        }
    }

    // 中序查找
    public HeroNode infixSearch(int no){
        if (this.root == null){
            System.out.println("当前节点为空");
            return null;
        }else{
            return root.infixSearch(no);
        }
    }
    // 后序查找
    public HeroNode postSearch(int no){
        if (this.root == null){
            System.out.println("当前节点为空");
            return null;
        }else{
            return root.postSearch(no);
        }
    }
}


// 表示节点
class HeroNode{
    private int no;
    private String name;  // 名字
    private HeroNode left; // 左节点
    private HeroNode right;// 右节点

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    public void preOrder(){
        // 前序是先输出当前节点在像左遍历, 随后向后
        System.out.println(this);
        // 向左遍历
        if (this.left != null) this.left.preOrder();
        // 向右遍历
        if (this.right != null) this.right.preOrder();
    }

    // 中序遍历
    public void infixOrder(){
        // 中序是先判断左节点, 如果左节点为空在 输出当前, 随后遍历右节点
        // 先问左边节点有没有
        if (this.left != null) this.left.infixOrder();
        // 在输出当前
        System.out.println(this);
        // 在问右边节点
        if (this.right != null) this.right.infixOrder();
    }

    // 后续遍历
    public void postOrder(){
        // 后续遍历是先判断左边有没有, 在看右边有没有, 都没有在回来输出当前节点
        // 问左边
        if (this.left != null) this.left.postOrder();
        // 问右边
        if (this.right != null) this.right.postOrder();
        // 输出当前
        System.out.println(this);
    }

    // 前序查找
    public HeroNode preSearch(int no){
        // 前序查找就是先比较当前节点, 在比较左节点, 随后比较右节点
        System.out.println("前序查找");
        if (this.no == no){
            return this;
        }
        // 如果当前不是向左边找
        HeroNode heroNode = null;
        if (this.left != null){
            heroNode = this.left.preSearch(no);
        }
        // 如果下一个还是null 说明没找到
        if (heroNode != null){
            return heroNode;
        }
        // 到这里说明没找到  向右节点找
        if (this.right != null){
            heroNode = this.right.preSearch(no);
        }
        // 如果找完了那直接返回即可
        return heroNode;
    }

    // 中序查找
    public HeroNode infixSearch(int no){
        // 中序查找首先判断左边有没有节点, 如果有就去左边, 判断当前, 随后判断右边
        HeroNode heroNode = null;
        // 先判断左边
        if (this.left != null){
            heroNode = this.left.infixSearch(no);
        }
        // 判断是否为空
        if (heroNode != null){
            return heroNode;
        }
        System.out.println(" 中序查找");
        // 经过上面说明左边没找到, 随后比较当前节点
        if (this.no == no){
            return this;
        }

        // 还是没找到说明要向右边找
        if (this.right != null){
            heroNode = this.right.infixSearch(no);
        }
        // 直接返回即可
        return heroNode;
    }

    // 后续查找
    public HeroNode postSearch(int no){
        // 后续查找是先判断左边, 在判断右边, 随后比较当前
        HeroNode heroNode = null;
        if (this.left != null){
            heroNode =  this.left.postSearch(no);
        }
        // 判断是否为空
        if (heroNode != null){
            return heroNode;
        }

        // 没找到判断右边
        if (this.right != null){
            heroNode = this.right.postSearch(no);
        }
        // 判断是否为空
        if (heroNode != null){
            return heroNode;
        }

        System.out.println(" 后续查找");
        // 都没找到判断当前
        if (this.no == no){
            return this;
        }
        return null;
    }
}
