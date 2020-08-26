package com.ty.binarySortTree;

/**
 * program : OneCode
 * description : 顺序二叉树
 * author : jyf
 * date : 2020-08-26 17:43
 **/
public class BinarySortTree {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinaryTree binaryTree = new BinaryTree();
        for (int i : arr) {
            binaryTree.add(new Node(i));
        }

        binaryTree.infixOrder();
    }
}

class BinaryTree{
    private Node root;

    // 添加
    public void add(Node node){
        if (root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    // 遍历
    public void infixOrder(){
        if (root == null){
            System.out.println("树为空不能遍历");
        }else{
            root.infixOrder();
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    // 添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            // 说明要向左挂
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 说明要向右挂
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}