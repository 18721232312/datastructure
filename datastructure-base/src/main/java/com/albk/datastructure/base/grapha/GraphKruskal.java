package com.albk.datastructure.base.grapha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 克鲁斯卡尔最小生成树
 * @team: ALBK
 * @date 2018/4/10 22:19
 */
public class GraphKruskal {
    /**
     * 存放所有的边
     */
    public List<Edge> edgeList;


    public GraphKruskal(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    /**
     * 最小生成树核心算法
     * 定义一个已经选择好的边数组selectedEdges，并初始化值为0，
     * selectedEdges 此数据下标表示边的起点，值表示边的终点。主要用来校验，是否存在回环。
     * selectedEdges[3]=5  说明，以3为起点，以5为终点的边，已经被使用。
     * <p>
     * 遍历每条边，
     * 从边的顶点在已选择的边中开始寻找，找到可达的终点 X
     * 从边的终点在已选择的边中开始寻找，找到可达的终点 Y
     * 如果X！=Y说明，当前的边可用，不会和已选择好的边构成回环。把当前边加放已选择的边的数组中，同时累加边的权值
     *
     * @return 最终可用边的权值之和
     */
    public int minSpanningTree() {
        int m, n, sum = 0;
        //已选择好的边数组
        int[] selectedEdges = new int[edgeList.size()];
        Arrays.fill(selectedEdges, 0);
        for (Edge edge : edgeList) {
            m = find(selectedEdges, edge.start);
            n = find(selectedEdges, edge.end);
            if (m != n) {
                selectedEdges[m] = n;
                sum += edge.getWeight();
                System.out.println("find " + m + " -> " + n + " : " + edge.getWeight());
            }
        }
        return sum;
    }

    /**
     * 在已选择的边的数组中，找当前起点可达的终点。
     * @param selectedArray  已选择好的边的数组
     * @param start  边的起点
     * @return 此起点在已选择好的数据中可达的终点
     */
    private int find(int[] selectedArray, int start) {
        while (selectedArray[start] != 0) {
            start = selectedArray[start];
        }
        return start;
    }

    /**
     * 边
     */
    static class Edge {
        //起点
        private int start;
        //终点
        private int end;
        //权值
        private int weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getEnd() {
            return end;
        }


        public int getWeight() {
            return weight;
        }

        public int getStart() {
            return start;
        }
    }

    public static void main(String[] args) {
        Edge e1 = new Edge(4, 7, 7);
        Edge e2 = new Edge(2, 8, 8);
        Edge e3 = new Edge(0, 1, 10);
        Edge e4 = new Edge(0, 5, 11);
        Edge e5 = new Edge(1, 8, 12);
        Edge e6 = new Edge(3, 7, 16);
        Edge e7 = new Edge(1, 6, 16);
        Edge e8 = new Edge(5, 6, 17);
        Edge e9 = new Edge(1, 2, 18);
        Edge e10 = new Edge(6, 7, 19);
        Edge e11 = new Edge(3, 4, 20);
        Edge e12 = new Edge(3, 8, 21);
        Edge e13 = new Edge(2, 3, 22);
        Edge e14 = new Edge(3, 6, 24);
        Edge e15 = new Edge(4, 5, 26);

        List<Edge> edgeList = new ArrayList();
        edgeList.add(e1);
        edgeList.add(e2);
        edgeList.add(e3);
        edgeList.add(e4);
        edgeList.add(e5);
        edgeList.add(e7);
        edgeList.add(e6);
        edgeList.add(e8);
        edgeList.add(e9);
        edgeList.add(e10);
        edgeList.add(e11);
        edgeList.add(e12);
        edgeList.add(e13);
        edgeList.add(e14);
        edgeList.add(e15);

        GraphKruskal kruskal = new GraphKruskal(edgeList);
        int res = kruskal.minSpanningTree();
        System.out.println("最小生成树大小为：" + res);
    }
}
