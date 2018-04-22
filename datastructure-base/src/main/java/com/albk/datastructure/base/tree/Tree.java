package com.albk.datastructure.base.tree;


import com.albk.datastructure.base.statck.Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author BK
 * @version V2.0
 * @description: 树
 * @team: ALBK
 * @date 2018/4/22 01:15
 */
public class Tree {

    public Tree() {
    }

    /**
     * 构造一颗二叉树
     *
     * @param treeArray
     */
    public TreeNode<String> createTree(String[] treeArray, int rootPos) {
        TreeNode<String> node = null;
        if (rootPos < treeArray.length) {
            if (!"#".equals(treeArray[rootPos])) {
                node = new TreeNode<String>(treeArray[rootPos]);
                node.left = createTree(treeArray, rootPos * 2 + 1);
                node.right = createTree(treeArray, rootPos * 2 + 2);
            }
        }
        return node;
    }

    /**
     * 前序遍历
     */
    public void preOrder(TreeNode<String> root, List<String> res) {
        if (root == null) {
            return;
        }
        res.add(root.value);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @param res
     */
    public void inOrder(TreeNode<String> root, List<String> res) {
        if (root == null) {
            return;
        }
        inOrder(root.left, res);
        res.add(root.value);
        inOrder(root.right, res);
    }

    /**
     * 后序遍历
     */
    public void postOrder(TreeNode<String> root, List<String> res) {
        if (root == null) {
            return;
        }
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.value);
    }

    /**
     * 获取树的高度
     *
     * @param root 根节点
     * @return 树的高度
     */
    public int getTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = getTreeHeight(root.left);
        int rh = getTreeHeight(root.right);
        return Math.max(lh, rh) + 1;
    }

    /**
     * 获取节点数量
     *
     * @param root
     * @return
     */
    public int getTreeSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getTreeSize(root.left) + getTreeSize(root.right) + 1;
    }

    /**
     * 使用栈结构前序遍历
     *
     * @param root
     * @param res
     */
    public void stackPreOrder(TreeNode<String> root, List<String> res) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<String> element = stack.pop();
            res.add(element.value);
            if (element.right != null) {
                stack.push(element.right);
            }
            if (element.left != null) {
                stack.push(element.left);
            }
        }
    }


    /**
     * 层序遍历
     * 递归
     */
    public void levelOrder(TreeNode<String> root) {
        if (root == null) {
            return;
        }
        int height = getTreeHeight(root);
        for (int i = 1; i <= height; i++) {
            levelOrder(root, i);
        }
    }

    private void levelOrder(TreeNode<String> node, int level) {
        if (node == null || level < 1) {
            return;
        }
        if (level == 1) {
            System.out.print(node.value + "  ");
            return;
        }
        // 左子树
        levelOrder(node.left, level - 1);
        // 右子树
        levelOrder(node.right, level - 1);
    }

    /**
     * 前序遍历
     * 非递归
     */
   /* public void preOrder1(TreeNode<String> node) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.value + " ");
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }*/


    /**
     * 中序遍历
     * 非递归
     */
    /*public void midOrder1(TreeNode<String> Node) {
        Stack<TreeNode> stack = new Stack<>();
        while (Node != null || !stack.isEmpty()) {
            while (Node != null) {
                stack.push(Node);
                Node = Node.left;
            }
            if (!stack.isEmpty()) {
                Node = stack.pop();
                System.out.print(Node.value + "   ");
                Node = Node.right;
            }
        }
    }*/

    /**
     * 后序遍历
     * 非递归
     */
   /* public void posOrder1(TreeNode<String> Node) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while (Node != null || !stack1.isEmpty()) {
            while (Node != null) {
                stack1.push(Node);
                stack2.push(0);
                Node = Node.left;
            }

            while (!stack1.isEmpty() && stack2.peek() == i) {
                stack2.pop();
                System.out.print(stack1.pop().value + "   ");
            }

            if (!stack1.isEmpty()) {
                stack2.pop();
                stack2.push(1);
                Node = stack1.peek();
                Node = Node.right;
            }
        }
    }*/

    /**
     *  bfs  层序遍历
     */
    public void bfs(TreeNode<String> Node, List<String> res) {
        if (Node == null) {
            return;
        }

        TreeNode<String> TreeNode;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(Node);
        while (!queue.isEmpty()) {
            TreeNode = queue.poll();
            res.add(TreeNode.value);
            if (TreeNode.left != null) {
                queue.offer(TreeNode.left);
            }
            if (TreeNode.right != null) {
                queue.offer(TreeNode.right);
            }
        }
    }


    public static void main(String[] args) {
        String[] treeArray = new String[]{"1", "2", "3", "4", "5", "#", "6", "#", "#", "7", "8"};
        Tree tree = new Tree();
        TreeNode<String> root = tree.createTree(treeArray, 0);
        System.out.println("tree height is : " + tree.getTreeHeight(root));
        System.out.println("tree size is : " + tree.getTreeSize(root));

        List<String> res = new ArrayList<>();
        tree.preOrder(root, res );
        System.out.println("preOrder result is : "+res);

        res = new ArrayList<>();
        tree.inOrder(root, res );
        System.out.println("inOrder result is : "+res);

        res = new ArrayList<>();
        tree.postOrder(root, res );
        System.out.println("postOrder result is : "+res);

        res = new ArrayList<>();
        tree.stackPreOrder(root, res );
        System.out.println("stack result is : "+res);

        res = new ArrayList<>();
        tree.bfs(root ,res);
        System.out.println("bfs order is : "+res);

        res = new ArrayList<>();
        tree.levelOrder(root);
        System.out.println("lever order is : " + res);

    }
}
