package com.ty.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * program : OneCode
 * description : 图类型的数据结构
 * author : jyf
 * date : 2020-08-31 15:38
 **/
public class Graph {

    ArrayList<String> vertexList; // 存储我们的数据类型
    int[][] edges; // 表示图这个结构
    int numOfEdges; // 表示边的数量


    public static void main(String[] args) {
        String vertexS[] = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(vertexS.length);
        for (String vertex : vertexS) {
            graph.insertVertex(vertex);
        }

        // 添加里面的节点
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 显示效果
        graph.showEdges();
        // 显示边
        System.out.println(graph.numOfEdges);


    }

    /**
     * 构造一个图
     * @param n  节点个数
     */
    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
    }

    /**
     * 把传入的符号存入到集合
     * @param v1
     */
    public void insertVertex(String v1){
        vertexList.add(v1);
    }

    /**
     *
     * @param eg1 需要连接的节点1
     * @param eg2 需要连接的节点2
     * @param index 权值
     */
    public void insertEdge( int eg1 , int eg2,int index){
        edges[eg1][eg2] = 1;
        edges[eg2][eg1] = 1;
        numOfEdges++;
    }

    public void showEdges(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
