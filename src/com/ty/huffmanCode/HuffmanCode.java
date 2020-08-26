package com.ty.huffmanCode;


import java.io.*;
import java.util.*;

/**
 * program : OneCode
 * description : 赫夫曼编码压缩
 * author : jyf
 * date : 2020-08-21 14:27
 **/
public class HuffmanCode {

    public static void main(String[] args) {
        /*
        String str = "i like like like java do you like a java";
        // 先把当前字符串的元素个数统计出来
        Map<Byte, Integer> counts = getCounts(str.getBytes());
        // 1 我们需要先转成一个集合 来方便操作
        List<Node> huffman = createHuffmanList(counts);
        // 转成赫夫曼树
        Node node = createHuffman(huffman);
        // 随后把当前树的哈夫曼编码获取出来
        getCodes(node, "", sb);
        // 随后转成字符串 然后转成字节数组
        byte[] huffmanByte = zip(str.getBytes());
        // 解压后的字节数组
        byte[] decode = decode(codes, huffmanByte);
        */

        //zipFile("C:\\Users\\Administrator\\Desktop\\尚硅谷数据结构和算法\\资料\\压缩测试文件\\src.bmp", "C:\\Users\\Administrator\\Desktop\\尚硅谷数据结构和算法\\资料\\压缩测试文件\\sdst.bmp");
        // unZipFile("C:\\Users\\Administrator\\Desktop\\尚硅谷数据结构和算法\\资料\\压缩测试文件\\sdst.bmp", "C:\\Users\\Administrator\\Desktop\\尚硅谷数据结构和算法\\资料\\压缩测试文件\\unzip.bmp");
    }

    static StringBuilder sb = new StringBuilder();
    static Map<Byte, String> codes = new HashMap<>();

    /**
     *  压缩一个文件
     * @param srcFile  源文件
     * @param dstFile  目标文件
     */
    public static void zipFile(String srcFile, String dstFile){
        // 1. 我们需要先拿到文件输入流.
        InputStream is = null;
        ObjectOutputStream oos = null;
        try {
            // 获取源文件输入流
            is = new FileInputStream(srcFile);
            // 通过字节长度创建超大字节数组
            byte[] data = new byte[is.available()];
            is.read(data); // 这样data里面就存放了对应的数据
            // 1.1 我们需要获取到对应的统计
            Map<Byte, Integer> counts = getCounts(data);
            // 1.2 需要转成一个集合方便操作
            List<Node> huffmanList = createHuffmanList(counts);
            // 1.3然后需要拿到这个node树
            Node huffman = createHuffman(huffmanList);
            // 1.4随后我们需要获取当前的哈夫曼表
            getCodes(huffman,"",sb);
            // 1.5我们直接拿着哈夫曼表对树进行压缩
            byte[] zip = zip(data);  // 返回的就是字符串压缩以后的数组
            // 压缩完了我们就可以把当前字节数组压缩出去
            oos = new ObjectOutputStream(new FileOutputStream(dstFile));
            oos.writeObject(zip); // 输出压缩后的字节数组
            oos.writeObject(codes); // 我们也需要把我们的哈夫曼表给输出
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                assert oos != null;
                oos.close();
            is.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * 解压我们的文件
     * @param srcZip 需要被解压的文件
     * @param dstZip  解压后的源文件
     */
    public static void unZipFile(String srcZip,String dstZip){
        // 1.我们需要把字节数组以及哈夫曼编码表获取出来
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            // 获取源文件
            ois = new ObjectInputStream(new FileInputStream(srcZip));
            // 先存先取, 我们先取压缩后的字节数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 在去哈夫曼表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            // 直接解密获得字节数组
            byte[] unBytes = decode(huffmanCodes, huffmanBytes);
            // 需要字节输出流
            fos = new FileOutputStream(dstZip);
            fos.write(unBytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                fos.close();
                ois.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 把我们的字节通过哈夫曼编码表反转成我们的字符串
     * @param b    字节
     * @param flag 是否是最后一个字符串,  不是的话我们需要补高位
     * @return
     */
    private static String byteToBitString(byte b, boolean flag) {
        // 我们发现只有int里面有方法解码所以我们吧b转成int
        int intByte = b;
        if (!flag) {
            // 这里是如果不是最后一位,我们需要补高位
            intByte |= 256;
        }
        // 转成二进制
        String s = Integer.toBinaryString(intByte);

        // 判断是否是最后一位
        if (!flag || b < 0) {
            // 需要补码的则需要截取后8位
            return s.substring(s.length() - 8);
        }
        // 说明直接返回即可  是最后一位
        return s;


    }

    /**
     * 解码成原字节数组
     *
     * @return
     */
    private static byte[] decode(Map<Byte, String> codes, byte[] huffmanByte) {
        // 1. 首先我们需要把这个字节数组, 根据我们的哈夫曼编码转成对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanByte.length; i++) {
            byte b = huffmanByte[i];
            // 判断是否是最后一个元素
            boolean flag = i == huffmanByte.length - 1;
            stringBuilder.append(byteToBitString(b, flag));
        }
        // 我们接下来需要根据字符串去匹配对应的哈夫曼编码表里面的数, 也就是string 为 key  byte 为value
        Map<String, Byte> decodeMap =  new HashMap<>();
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        // 这一步已经完成了字符串的拼接了, 接下来就是把元素一个个匹配出来
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            String substring;
            Byte b;
            while (true) {
                // 先截取出一段字符串,然后去比较
                 substring = stringBuilder.substring(i, i + count);
                 b = decodeMap.get(substring);
                if (b == null){
                    count++;
                }else{
                    // 如果有这个数那我们需要存起来
                    byteList.add(b);
                    i+=count;
                    break;
                }
            }
        }
        // 到这里我们list已经存放好了所有的数据.
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
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


    /**
     * 把传入的赫夫曼树节点, 放入集合中
     * @param node 传入的节点
     * @param code 下一个走的路径
     * @param stringBuilder  当前拼接的字符串
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

    /**
     * 根据集合创建哈夫曼树
     * @param huffman  所有节点集合
     * @return 最后的根节点
     */
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
