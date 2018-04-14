package com.albk.datastructure.base.graph;

import java.util.*;

/**
 * @author BK
 * @version V2.0
 * @description: 图的基本操作和遍历
 * @team: ALBK
 * @date 2018/4/14 0:04
 */
public class Graph {
    /**
     * 最大权值，用来标记顶点不可达
     */
    private static final int MAX_WEIGHT = 888;
    /**
     * 顶点数量
     */
    private int vertexSize;
    /**
     * 顶点数组
     */
    private int[] vertexs;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    public Graph(int vertexSize) {
        this.vertexSize = vertexSize;
        vertexs = new int[vertexSize];
        matrix = new int[vertexSize][vertexSize];
        visited = new boolean[vertexSize];
        res = new ArrayList<>();
    }

    public int getVertexSize() {
        return vertexSize;
    }


    public int[] getVertexs() {
        return vertexs;
    }


    public int[][] getMatrix() {
        return matrix;
    }

    public static int getMaxWeight() {
        return MAX_WEIGHT;
    }

    /**
     * 获取某顶点的出度
     *
     * @param vertex
     * @return
     */
    private int getOutDegree(int vertex) {
        int res = 0;
        for (int i = 0; i < getVertexSize(); i++) {
            if (getMatrix()[vertex][i] != 0 && getMatrix()[vertex][i] != MAX_WEIGHT) {
                res++;
            }
        }
        return res;
    }

    /**
     * 获取某顶点的入度
     */
    private int getInDegree(int vertex) {
        int res = 0;
        for (int i = 0; i < getVertexSize(); i++) {
            if (getMatrix()[i][vertex] != 0 && getMatrix()[i][vertex] != MAX_WEIGHT) {
                res++;
            }
        }
        return res;
    }

    /**
     * 获取两个顶点间的权值
     *
     * @param v1 顶点
     * @param v2 顶点
     * @return -1 表示不连通   0 表示是自己与自己，  其它 ，权值
     */
    private int getWeight(int v1, int v2) {
        int weight = matrix[v1][v2];
        return weight == MAX_WEIGHT ? -1 : (weight == 0) ? 0 : weight;
    }


    /**
     * 是否是连通的
     *
     * @param val
     * @return
     */
    private boolean isConnected(int val) {
        return val != 0 && val != MAX_WEIGHT;
    }

    private boolean[] visited;
    private List<Integer> res;

    /**
     * 递归方式 while方式  dfs
     */
    public List<Integer> dfs2(int v) {
        visited[v] = true;
        res.add(v);
        int first = getOneNoVisitedElement(v);
        while (first != -1) {
            dfs2(first);
            first = getOneNoVisitedElement(first);
        }
        return res;
    }

    /**
     * 递归方式   for循环版本 dfs
     *
     * @param vertex 顶点
     * @return
     */
    private List<Integer> dfs1(int vertex) {
        visited[vertex] = true;
        res.add(vertex);
        for (int i = 0; i < vertexSize; i++) {
            if (isConnected(matrix[vertex][i]) && !visited[i]) {
                dfs1(i);
            }
        }
        return res;
    }

    /**
     * 使用栈结构进行图遍历
     *
     * @return
     */
    public List<Integer> dfs() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        res.add(0);
        while (!stack.isEmpty()) {
            int topElement = stack.peek();//读栈顶元素
            visited[topElement] = true;
            int v = getOneNoVisitedElement(topElement);
            if (v != -1) {//说明找到了
                visited[v] = true;
                res.add(v);
                stack.push(v);
            } else {//只有当前栈顶元素不存在未被访问的邻接点时才弹出栈顶元素
                stack.pop();
            }
        }
        return res;
    }

    /**
     * 从顶点V可达的顶点中，找一个未被访问的顶点
     *
     * @param v 顶点
     * @return -1 未找到（即当前顶点的所有可达顶点已被访问完）   否则返回可达的顶点
     */
    private int getOneNoVisitedElement(int v) {
        int res = -1;
        /*找栈顶元素的第一个未被访问的邻接点*/
        for (int i = 0; i < vertexSize; i++) {
            if (isConnected(matrix[v][i]) && !visited[i]) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * 广度优先遍历
     *
     * @return
     */
    private List<Integer> bfs() {
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        while (!queue.isEmpty()) {
            int ele = queue.poll();
            res.add(ele);
            visited.add(ele);
            for (int i = 0; i < vertexSize; i++) {
                if (matrix[ele][i] != 0 && matrix[ele][i] != MAX_WEIGHT && !visited.contains(i)) {
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
        return res;
    }

    /**
     * 插入顶点  TODO
     * 删除顶点  TODO
     *
     * @param args
     */


    public static void main(String[] args) {
        testMatrix();
        testDfs();
        testBfs();
    }

    /**
     * 测试图dfs遍历
     */
    private static void testBfs() {
        Graph graph = buildGraph();
        System.out.println(graph.dfs1(0));
    }

    /**
     * 测试图dfs遍历
     */
    private static void testDfs() {
        Graph graph = buildGraph();
        System.out.println(graph.bfs());
    }

    /**
     * 测试图的基本操作
     */
    private static void testMatrix() {
        Graph graph = buildMatrix();
        System.out.println(graph.getOutDegree(2));
        System.out.println(graph.getInDegree(2));
        System.out.println(graph.getWeight(1, 0));
        System.out.println(graph.getWeight(4, 0));
        System.out.println(">>>>>>>>>>>>>>>>>>>");
    }

    public static Graph buildGraph() {
        Graph graph = new Graph(9);
        int[] a1 = new int[]{0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT};
        int[] a2 = new int[]{10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12};
        int[] a3 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8};
        int[] a4 = new int[]{MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21};
        int[] a5 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT};
        int[] a6 = new int[]{11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT};
        int[] a7 = new int[]{MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT};
        int[] a8 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT};
        int[] a9 = new int[]{MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0};

        graph.matrix[0] = a1;
        graph.matrix[1] = a2;
        graph.matrix[2] = a3;
        graph.matrix[3] = a4;
        graph.matrix[4] = a5;
        graph.matrix[5] = a6;
        graph.matrix[6] = a7;
        graph.matrix[7] = a8;
        graph.matrix[8] = a9;
        return graph;
    }

    private static Graph buildMatrix() {
        Graph graph = new Graph(5);
        int[] a0 = new int[]{0, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 6};
        int[] a1 = new int[]{9, 0, 3, MAX_WEIGHT, MAX_WEIGHT};
        int[] a2 = new int[]{2, MAX_WEIGHT, 0, 5, MAX_WEIGHT};
        int[] a3 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0, 1};
        int[] a4 = new int[]{MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0};
        graph.matrix[0] = a0;
        graph.matrix[1] = a1;
        graph.matrix[2] = a2;
        graph.matrix[3] = a3;
        graph.matrix[4] = a4;
        return graph;
    }
}
