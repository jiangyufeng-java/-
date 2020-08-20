package com.ty.huffmanTree;

import java.lang.reflect.Field;
import java.util.*;

/**
 * program : OneCode
 * description : 赫夫曼树
 * author : jyf
 * date : 2020-08-19 10:20
 **/
public class HuffmanTree {

    public static void main(String[] args) throws NoSuchFieldException {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node huffmanTree = createHuffmanTree(arr);

    }


    /**
     * 创建一个赫夫曼树
     * @param arr  需要创建的数组
     * @return  根节点
     */
    public static Node createHuffmanTree(int[] arr){
        // 首先吧数组转成集合待会方便操作
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        // 我们需要进行树化
        // 1.首先我们需要知道, 把最小的节点放在最下面, 也就是说从数组0 1 开始.
        // 每次完成以后把新的权重放入集合中,进行排序, 进行 0 1的权重加
        while (nodes.size() > 1){
            // 如果大于1 我们一直进行重复的操作
            // 现在我们需要对集合进行排序
            Collections.sort(nodes);

            // 取出  0 1的元素进行权重相加  也就是取出两个树
            Node node = nodes.get(0);
            Node node1 = nodes.get(1);
            Node temp = new Node(node.value + node1.value); // 生成的中间节点
            temp.left = node; // 左节点
            temp.right = node1; // 右节点
            // 现在需要把temp 放入集合, 随后删除node 和node1
            nodes.add(temp);
            nodes.remove(node);
            nodes.remove(node1);
        }
        return nodes.get(0);
    }
}


class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    // 前序遍历
    public void prePrint(){
        System.out.println(this);
        if (this.left != null){
            this.left.prePrint();
        }
        if (this.right != null) this.right.prePrint();
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
