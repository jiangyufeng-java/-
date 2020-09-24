package com.ty.tree;

/**
 * program : DataStructures
 * description : 顺序二叉树
 * author : jyf
 * date : 2020-08-13 09:39
 **/
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);

    }

}


class ArrBinaryTree {
    // 定义数组模拟树
    int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 前序遍历顺序二叉树1,2,4,5,3,6,7
    /**
     *  传入的是根节点
     *  目前只考虑满二叉树的情况
     * @param index 根节点
     */
    public void preOrder(int index){
        if (arr == null){
            System.out.println("树为空不能打印");
            return;
        }

        // 正常的话直接开始遍历, 前序遍历输出当前的节点
        System.out.println(arr[index]);
        // 左节点为 2 * index + 1;
        if ((2*index+1) < arr.length){
            // 说明这个节点是存在
            // 进去递归调用
            preOrder(2 * index + 1); // 左节点
        }

        // 右节点
        if ((2*index+2) < arr.length){
            // 说明这个节点是存在
            // 进去递归调用
            preOrder(2 * index + 2); // 右节点
        }
    }
}
