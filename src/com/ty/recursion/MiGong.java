package com.ty.recursion;

/**
 * program : DataStructures
 * description : 模拟迷宫问题
 * author : jyf
 * date : 2020-07-29 13:56
 **/
public class MiGong {

    public static void main(String[] args) {
        // 创建一个二维数组,模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下全部为墙
        for (int i = 0; i < 7; i++) {
            // 设置第一行和最后一行为墙
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 设置第一列和最后一列为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("迷宫图");
        for (int i = 0; i < 8; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                System.out.print(map[i][i1] + " ");
            }
            System.out.println();
        }

        setWay2(map, 1, 1);
        System.out.println("走过的迷宫图");
        for (int i = 0; i < 8; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                System.out.print(map[i][i1] + " ");
            }
            System.out.println();
        }

        // 接下来就定义方法让他走迷宫了



    }

    /**
     * 1. map为地图
     * 2. i,j 表示从地图哪里开始走
     * 3. 如果小球能找到map[5][6]的位置,说明通路找到了
     * 4. 约定map[i][j] 等于0表示没走过 ,等于1表示为墙 2表示走过是通的,3表示走过走不通
     * 5. 在走的时候我们需要定义一个策略看具体是怎么走: 我这里定义为 右->下->左->上
     * @param map  首先是地图
     * @param i  起始位置
     * @param j
     * @return 这个点能否走通
     */
    public static boolean setWay(int[][] map , int i,int j){
        if (i==6 && j==5){
            // 表示这个点已经找到了
            map[6][5] = 2;
            return true;
        }else{
            // 表示还没找到这个点.
            if (map[i][j] == 0){
                // 说明当前这个点是可以走的
                // 假定他是可以走的
                map[i][j] = 2;
                if (setWay(map, i, j+1)){
                    return true;
                }else if (setWay(map, i+1, j)){
                    return true;
                }else if (setWay(map, i, j-1)){
                    return true;
                }else if(setWay(map, i-1, j)){
                    return true;
                }else{
                    // 都走不通那么肯定就是死路,返回来标记走不了.
                    map[i][j] = 3;
                    return false;
                }
            }else{
                // 说明这个点不为0那么久直接返回false
                return false;
            }
        }
    }
    // 修改策略 变成  上-> 右 ->下 ->左
    public static boolean setWay2(int[][] map , int i,int j){
        if (i==6 && j==5){
            // 表示这个点已经找到了
            map[6][5] = 2;
            return true;
        }else{
            // 表示还没找到这个点.
            if (map[i][j] == 0){
                // 说明当前这个点是可以走的
                // 假定他是可以走的
                map[i][j] = 2;
                if (setWay2(map, i-1, j)){
                    return true;
                }else if (setWay2(map, i, j+1)){
                    return true;
                }else if (setWay2(map, i+1, j)){
                    return true;
                }else if(setWay2(map, i, j-1)){
                    return true;
                }else{
                    // 都走不通那么肯定就是死路,返回来标记走不了.
                    map[i][j] = 3;
                    return false;
                }
            }else{
                // 说明这个点不为0那么久直接返回false
                return false;
            }
        }
    }
}
