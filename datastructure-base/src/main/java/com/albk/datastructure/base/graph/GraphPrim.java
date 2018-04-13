package com.albk.datastructure.base.graph;

/**
 * @author BK
 * @version V2.0
 * @description: 普里姆算法
 * @team: ALBK
 * @date 2018/4/14 1:29
 */
public class GraphPrim {
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

    public GraphPrim(int vertexSize) {
        this.vertexSize = vertexSize;
        vertexs = new int[vertexSize];
        matrix = new int[vertexSize][vertexSize];
    }


    /**
     * 普里姆算法思路：
     * 第一步：构造最小成本的数组，默认值为第一个节点的数据
     * 第二步：在当前最小成本数组中，找出当前所有节点 ，连接成本最小的顶点信息
     * 累加权重，并将最小成本中对应节点的信息置为0 ，表示当前节点的最小成本节点已经找到，不需要再做处理
     * 第三步：由第二步得到的最小成本顶点，将该顶点所有可达顶点的成本放于最小成本数组中
     * 第四步：重复第二步，再找出由当前顶点可到达的顶点中，成本最小的顶点
     * 第五步：重复第三步
     * 直到所有的顶点都 遍历完成，
     */
    public void prim() {
        int[] lowCost = initLowCost(matrix[0]);
        int sum = 0;
        for (int x = 1; x < vertexSize; x++) {
            int minPosition = 0;
            int minWeight = MAX_WEIGHT;
            /*
             *   找出最小成本数组中，最小权值边的信息
             */
            for (int v = 0; v < vertexSize; v++) {
                if (lowCost[v] > 0 && lowCost[v] < minWeight) {
                    minPosition = v;
                    minWeight = lowCost[v];
                }
            }
            System.out.println("找到边，成本为：" + minWeight);
            //将最小的权值加累加
            sum += minWeight;
            //在最小成本数组中置为0 标识已经找到该顶点的最小值
            lowCost[minPosition] = 0;
            //找最小边的下一条最小边的信息
            for (int i = 0; i < vertexSize; i++) {
                if (lowCost[i] > 0 && matrix[minPosition][i] < lowCost[i]) {
                    lowCost[i] = matrix[minPosition][i];
                }
            }
        }
        System.out.println("最小生成树：" + sum);
    }

    /**
     * 思路：
     * 第一步：构造最小成本数组，以第一个顶点的数据为初始数据
     * 第二步：创建剩余未处理顶点数量 初始值为顶点数量-1(第一个节点是初始值，默认已处理)
     * 剩余数量不等于0的时候，进行循环
     * 循环内核心逻辑：
     * 1、从最小成本数组中找到，当前节点中，可连接的最小成本
     * 2、累加成本，同时设置最小成本数组对应顶点的内容为0 ，标记此顶点已经处理
     * 3、从第一步找出的最小成本顶点，将该顶点所有可达顶点的成本放于最小成本数组中
     * 4、重复1，直到剩余节点数量为0
     */
    public void prim1() {
        int[] lowCost = initLowCost(matrix[0]);
        int res = 0;
        //剩余未处理节点数量
        int remain = vertexSize - 1;
        while (remain > 0) {
            int minPosition = 0;
            int minWeight = MAX_WEIGHT;
            for (int i = 0; i < lowCost.length; i++) {
                if (lowCost[i] != 0 && lowCost[i] < minWeight) {
                    minPosition = i;
                    minWeight = lowCost[i];
                }
            }
            System.out.println("找到边，成本为：" + minWeight);
            res += minWeight;
            lowCost[minPosition] = 0;
            for (int x = 0; x < vertexSize; x++) {
                if (lowCost[x] > 0 && matrix[minPosition][x] < lowCost[x]) {
                    lowCost[x] = matrix[minPosition][x];
                }
            }
            remain--;
        }
        System.out.println("最小生成树：" + res);
    }

    /**
     * 初始化最小成本数组
     *
     * @param array
     * @return
     */
    private int[] initLowCost(int[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }

    public static void main(String[] args) {
        GraphPrim graphPrim = buildGraph();
        graphPrim.prim();
        System.out.println("============================");
        graphPrim.prim1();
    }

    /**
     * 先构造邻接矩阵
     */
    private static GraphPrim buildGraph() {
        GraphPrim graph = new GraphPrim(9);
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

    private static GraphPrim buildMatrix() {
        GraphPrim graph = new GraphPrim(5);
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
