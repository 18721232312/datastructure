package com.albk.datastructure.base.graph;

/**
 * @author BK
 * @version V2.0
 * @description: 迪杰斯特拉
 * 此算法与普里姆算法高度相似 ，唯一不同的地主是， 在最终结果的数据组中，存放的内容不一样，
 * 理解这两点特别重要，算法唯一的区别就是，在找下一个节点时候的核心逻辑有些差异
 * 普里姆算法存放的最小权值，
 * 迪杰斯特拉存放的是，顶点，到另一个顶点的最短路径的值。
 * @team: ALBK
 * @date 2018/4/14 14:29
 */
public class GraphDijstra {
    /**
     * 迪杰斯特拉算法思路：
     * 第一步：构造最短路径的数组，默认值为第一个顶点的数据
     * 第二步：在当前最小成本数组中，找出当前所有节点 ，连接成本最小的顶点信息
     * 并将最已找出数据中对应的值设置为true，表示当前节点的最小路径已经找到
     * 第三步：从第二步得到的最小路径的顶点，找与该顶点相连的顶点中，与上个节点的最小值累加值，与最小成本路径中的对应的值相比，将最小的值更新成最小路径
     * 第四步：重复第二步，再找出由当前顶点可到达的顶点中，成本路径的顶点
     * 第五步：重复第三步
     * 直到所有的顶点都 遍历完成，
     */
    public void getShortPath(Graph graph) {

        //对应节点的最小路径 下标是顶点，值是最小路径
        int[] sortPath = graph.getMatrix()[0];
        //是否已经找到
        boolean[] hasFind = new boolean[graph.getVertexSize()];
        for (int i = 0; i < graph.getVertexSize(); i++) {
            int minPath = graph.getMaxWeight();
            int minVex = 0;
            //找出与当前顶点路径最短的节点信息
            for (int k = 0; k < sortPath.length; k++) {
                if (!hasFind[k] && sortPath[k] < minPath) {
                    minVex = k;
                    minPath = sortPath[k];
                }
            }
            hasFind[minVex] = true;
            for (int k = 0; k < graph.getVertexSize(); k++) {
                if (!hasFind[k] && minPath + graph.getMatrix()[minVex][k] < sortPath[k]) {
                    sortPath[k] = graph.getMatrix()[minVex][k] + minPath;
                }
            }
        }
        for (int i = 0; i < sortPath.length; i++) {
            System.out.println("顶点0到 " + i + "的最短路径是:" + sortPath[i]);
        }
    }

    public static void main(String[] args) {
        GraphDijstra dijstra = new GraphDijstra();
        Graph graph = Graph.buildGraph();
        dijstra.getShortPath(graph);
    }
}
