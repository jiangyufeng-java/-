package com.ty.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * program : DataStructures
 * description : 图类型的数据结构
 * author : jyf
 * date : 2020-08-31 15:38
 **/
public class Graph {

    ArrayList<String> vertexList; // 存储我们的数据类型
    int[][] edges; // 表示图这个结构
    int numOfEdges; // 表示边的数量
    boolean[] isVisited;

    public static void main(String[] args) {
//        String vertexS[] = {"A", "B", "C", "D", "E"};
        String vertexS[] = {"1", "2", "3", "4", "5" ,"6","7","8"};
        Graph graph = new Graph(vertexS.length);
        for (String vertex : vertexS) {
            graph.insertVertex(vertex);
        }

        // 添加里面的节点
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示效果
//        graph.showEdges();
        // 显示边
        System.out.println(graph.numOfEdges);
        graph.dfs();

    }

    /**
     * 构造一个图
     * @param n  节点个数
     */
    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
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
        edges[eg1][eg2] = index;
        edges[eg2][eg1] = index;
        numOfEdges++;
    }

    public void showEdges(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    // 获取当前节点的下一个节点
    public int getFirstNeighbor(int i){
        for (int j = i+1; j < vertexList.size(); j++) {
            if (edges[i][j] > 0){
                // 表示他存在
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先搜索
     * @param v
     */
    private void dfs(int v){
        // 1. 直接输出当前节点
        System.out.print(getValueByIndex(v) + "->");
        // 2. 把当前节点标记成已访问
        isVisited[v] = true;
        // 3. 获取下一个节点
        int w = getFirstNeighbor(v);
        // 4.若firstNeighbor 不为-1 说明有相邻节点
        while (w != -1) {
            if (!isVisited[w]){
                // 表示这个节点没有被访问过
                dfs(w);
            }else{
                w = getNextNeighbor(v,w);
            }

        }
    }

    /**
     * 深度优先算法
     */
    public void dfs(){
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                dfs(i);
            }
        }
    }

    private int getNextNeighbor(int v, int w) {
        for (int j = (w+1); j < vertexList.size(); j++){
            if (edges[v][j] > 0){
                // 表示节点的邻接点存在直接返回
                return j;
            }
        }
        return -1;
    }

    private String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    private void bfs(int i){
        int u;
        int w;
        // 输出当前节点
        System.out.print(getValueByIndex(i)+"=>");
        // 设置为已访问
        isVisited[i] = true;
        // 当前节点添加到队列里面
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);
        while (!queue.isEmpty()){
            // 获取出队列头
            u = queue.removeFirst(); // 删除并出队列
            w = getFirstNeighbor(u);
            while (w != -1){
                if (!isVisited[w]){
                    // 表示没被访问
                    System.out.print(getValueByIndex(w)+"=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }

    }

    /**
     * 广度优先算法入口
     */
    public void bfs(){
        isVisited = new boolean[isVisited.length];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                bfs(i);
            }
        }
    }


}
