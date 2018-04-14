package com.albk.datastructure.base.graph;

import com.albk.datastructure.base.statck.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description: 图和拓扑排序  使用邻接表
 * @team: ALBK
 * @date 2018/4/14 17:11
 */
public class GraphTopologicalsort {
    /**
     * 表示图的邻接表
     */
    private VertexNode[] table;
    /**
     * 顶点数量
     */
    private int vertexSize;

    public GraphTopologicalsort(int vertexSize) {
        this.vertexSize = vertexSize;
    }

    /**
     * 邻接表
     */
    private static class VertexNode {
        /**
         * 顶点的入度
         */
        private int inDegree;
        private String data;
        /**
         * 顶点出度的边
         */
        private EdgeNode edgeNode;

        public VertexNode(int inDegree, String data) {
            this.inDegree = inDegree;
            this.data = data;
        }

        public int getInDegree() {
            return inDegree;
        }

        public void setInDegree(int inDegree) {
            this.inDegree = inDegree;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public EdgeNode getEdgeNode() {
            return edgeNode;
        }

        public void setEdgeNode(EdgeNode edgeNode) {
            this.edgeNode = edgeNode;
        }
    }

    /**
     * 边节点
     */
    private class EdgeNode {
        private int vertex;
        private String value;
        private EdgeNode next;

        public EdgeNode(int vertex) {
            this.vertex = vertex;
        }

        public int getVertex() {
            return vertex;
        }

        public void setVertex(int vertex) {
            this.vertex = vertex;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public EdgeNode getNext() {
            return next;
        }

        public void setNext(EdgeNode next) {
            this.next = next;
        }
    }

    /**
     * 　拓扑排序
     * 在拓扑网络中，有些任务是需要一些前置任务的条件，所以开始只能从入度为0的节点开始，至于从哪个节点开始是无所谓
     * 入度为0 的节点，是可以并行的，需要给出一种解决方案，完成拓扑排序
     * 思路：图表示 ，使用邻接表来完成
     * 第一步：建立堆或者栈
     * 第二步：遍历邻接表，将入度为0的顶点ID入队或者是入栈
     * 第三步：当栈中元素不为空时，则进行循环处理
     * 1、弹出第一个元素，同时记录已处理数+1(实际场景中，意味着这个已经完成，所以对于他的后续任务的入度需要减1)
     * 2、将此顶点的后续节点的入度进行减1 ，如果入度为0 则说明当前顶点/任务是可以运行，则进入栈/队操作，
     * 如果入度不为0，则将入度减1
     * 第四步：栈/队中元素为空，说明则处理完成，处理完成后比较已处理节点和图中顶点/任务数，是否相等，相等则排序完成
     * 如果不相等，说明有环存在 ，无法完成拓扑排序
     */
    public void sort() {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < table.length; i++) {
            if (table[i].inDegree == 0) {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            count++;
            res.add(top);
            VertexNode node = table[top];
            EdgeNode first = node.getEdgeNode();
            while (first != null) {
                if (--table[first.vertex].inDegree == 0) {
                    stack.push(first.vertex);
                }
                first = first.next;
            }
        }
        if (count != table.length) {
            throw new RuntimeException("无法完成拓扑排序!");
        }
        for (Integer re : res) {
            System.out.println("顶点" + re + "排序完成 ！ ");
        }
    }

    public void createVertexNodeTable() {
        VertexNode node0 = new VertexNode(0, "v0");
        VertexNode node1 = new VertexNode(0, "v1");
        VertexNode node2 = new VertexNode(2, "v2");
        VertexNode node3 = new VertexNode(0, "v3");
        VertexNode node4 = new VertexNode(2, "v4");
        VertexNode node5 = new VertexNode(3, "v5");
        VertexNode node6 = new VertexNode(1, "v6");
        VertexNode node7 = new VertexNode(2, "v7");
        VertexNode node8 = new VertexNode(2, "v8");
        VertexNode node9 = new VertexNode(1, "v9");
        VertexNode node10 = new VertexNode(1, "v10");
        VertexNode node11 = new VertexNode(2, "v11");
        VertexNode node12 = new VertexNode(1, "v12");
        VertexNode node13 = new VertexNode(2, "v13");
        table = new VertexNode[vertexSize];
        table[0] = node0;
        table[1] = node1;
        table[2] = node2;
        table[3] = node3;
        table[4] = node4;
        table[5] = node5;
        table[6] = node6;
        table[7] = node7;
        table[8] = node8;
        table[9] = node9;
        table[10] = node10;
        table[11] = node11;
        table[12] = node12;
        table[13] = node13;
        node0.edgeNode = new EdgeNode(11);
        node0.edgeNode.next = new EdgeNode(5);
        node0.edgeNode.next.next = new EdgeNode(4);
        node1.edgeNode = new EdgeNode(8);
        node1.edgeNode.next = new EdgeNode(4);
        node1.edgeNode.next.next = new EdgeNode(2);
        node2.edgeNode = new EdgeNode(9);
        node2.edgeNode.next = new EdgeNode(6);
        node2.edgeNode.next.next = new EdgeNode(5);
        node3.edgeNode = new EdgeNode(13);
        node3.edgeNode.next = new EdgeNode(2);
        node4.edgeNode = new EdgeNode(7);
        node5.edgeNode = new EdgeNode(12);
        node5.edgeNode.next = new EdgeNode(8);
        node6.edgeNode = new EdgeNode(5);
        node8.edgeNode = new EdgeNode(7);
        node9.edgeNode = new EdgeNode(11);
        node9.edgeNode.next = new EdgeNode(10);
        node10.edgeNode = new EdgeNode(13);
        node12.edgeNode = new EdgeNode(9);
        this.vertexSize = table.length;
    }


    public static void main(String[] args) {
        GraphTopologicalsort topologicalsort = new GraphTopologicalsort(14);
        topologicalsort.createVertexNodeTable();
        topologicalsort.sort();
    }

}
