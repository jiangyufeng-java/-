package com.ty.huffmanCode;

import java.util.*;

/**
 * program : OneCode
 * description : 赫夫曼编码压缩
 * author : jyf
 * date : 2020-08-21 14:27
 **/
public class HuffmanCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        // 先把当前字符串的元素个数统计出来
        Map<Byte, Integer> counts = getCounts(str.getBytes());
        System.out.println(counts);
        // 1 我们需要先转成一个集合 来方便操作
        List<Node> huffman = createHuffmanList(counts);
        System.out.println(huffman);
        // 转成赫夫曼树
        Node node = createHuffman(huffman);
        node.preOrder();
        // 随后把当前树的哈夫曼编码获取出来
        getCodes(node, "", sb);
        System.out.println(codes);
        // 随后转成字符串 然后转成字节数组
        byte[] huffmanByte = zip(str.getBytes());
        System.out.println(Arrays.toString(huffmanByte));
        System.out.println(sb.toString());
    }

    private static byte[] zip(byte[] bytes) {
        // 先拼接成一个字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(codes.get(b));
        }
        // 需要注意的是,如果/8 不够的话, 会小一点, 所以我们这里直接+7 在除,这样不影响最后的结果
        byte[] huffman = new byte[(sb.length() + 7) / 8];
        int index = 0;
        // 随后我们需要8位一个位置
        for (int i = 0; i < sb.length(); i += 8) {
            String substring;
            // 如果这个数小于
            if (i + 8 < sb.length()) {
                substring = sb.substring(i, i + 8);
            } else {
                substring = sb.substring(i);
            }
            // 把当前数转成二进制
            huffman[index++] = (byte) Integer.parseInt(substring, 2);
        }
        return huffman;
    }


    static StringBuilder sb = new StringBuilder();
    static Map<Byte, String> codes = new HashMap<>();

    /**
     * 把传入的赫夫曼树节点, 放入集合中
     *
     * @param node
     * @param code
     * @param stringBuilder
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        // 每次创建一个新对象的目的是为了方面递归用的是新对象
        StringBuilder sb1 = new StringBuilder(stringBuilder);
        sb1.append(code);
        if (node.left != null) {
            // 说明左边还可以走
            getCodes(node.left, "0", sb1);
        }

        if (node.right != null) {
            getCodes(node.right, "1", sb1);
        }
        if (node.value != null) {
            codes.put(node.value, sb1.toString());
        }
    }

    private static Node createHuffman(List<Node> huffman) {
        while (huffman.size() > 1) {
            // 需要先进行排序
            Collections.sort(huffman);
            // 拿到最小的两个数
            Node left = huffman.get(0);
            Node right = huffman.get(1);
            // 两个节点组成一个节点
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            // 删除
            huffman.remove(left);
            huffman.remove(right);
            huffman.add(parent);
        }
        return huffman.get(0);
    }

    /**
     * 传入字节数组统计字符串的个数
     *
     * @param bytes
     * @return
     */
    private static Map<Byte, Integer> getCounts(byte[] bytes) {
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                // 说明这个数不存在
                counts.put(b, 1);
            } else {
                // 说明这个数存在
                counts.put(b, count + 1);
            }
        }
        return counts;
    }

    /**
     * 把当前这个map转成Node集合
     *
     * @param maps 统计好 的元素
     * @return
     */
    public static List<Node> createHuffmanList(Map<Byte, Integer> maps) {
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> map : maps.entrySet()) {
            nodes.add(new Node(map.getKey(), map.getValue()));
        }
        return nodes;
    }

}

class Node implements Comparable<Node> {
    Byte value;  // 具体的值
    int weight; // 权值
    Node left;
    Node right;

    public Node(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
