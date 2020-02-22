package com.albk.datastructure.base.tree20200222;

import java.util.*;

/**
 * @author BK
 * @description: 二叉树复习
 * @date 2020/2/22 11:34 上午
 */
public class Tree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.addAll(Arrays.asList("3", "2", "9", "", "", "10", "", "", "8", "", "4"));

        TreeNode treeNode = tree.buildTree(linkedList);
        List<Integer> integers = tree.preOrderTraversal(treeNode);
        System.out.println("preOrderTraversal: ");
        for (Integer i : integers) {
            System.out.print(i + "  ");
        }
        System.out.println();
        integers = tree.midOrderTraversal(treeNode);
        System.out.println("midOrderTraversal: ");
        for (Integer i : integers) {
            System.out.print(i + "  ");
        }
        System.out.println();
        integers = tree.postOrderTraversal(treeNode);
        System.out.println("postOrderTraversal: ");
        for (Integer i : integers) {
            System.out.print(i + "  ");
        }

        System.out.println();
        integers = tree.levelOrderTraversal(treeNode);
        System.out.println("levelOrderTraversal: ");
        for (Integer i : integers) {
            System.out.print(i + "  ");
        }
        System.out.println();
        integers = tree.dfs(treeNode);
        System.out.println("dfs: ");
        for (Integer i : integers) {
            System.out.print(i + "  ");
        }

        System.out.println();
    }

    /**
     * 构建树
     *
     * @param linkedList
     * @return
     */
    public TreeNode buildTree(LinkedList<String> linkedList) {
        TreeNode root = null;
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }
        String e = linkedList.removeFirst();
        if (!"".equals(e)) {
            root = new TreeNode();
            root.value = Integer.parseInt(e);
            root.left = buildTree(linkedList);
            root.right = buildTree(linkedList);
        }
        return root;
    }

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.value);
        res.addAll(preOrderTraversal(root.left));
        res.addAll(preOrderTraversal(root.right));
        return res;
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(postOrderTraversal(root.left));
        res.addAll(postOrderTraversal(root.right));
        res.add(root.value);
        return res;
    }

    /**
     * 是序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> midOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(midOrderTraversal(root.left));
        res.add(root.value);
        res.addAll(midOrderTraversal(root.right));
        return res;
    }

    /**
     * 层序遍历 bfs
     *
     * @param root
     * @return
     */
    public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode e = queue.poll();
            if (e.left != null) {
                queue.offer(e.left);
            }
            if (e.right != null) {
                queue.offer(e.right);
            }
            res.add(e.value);
        }
        return res;
    }

    /**
     * dfs
     *
     * @param root
     * @return
     */
    public List<Integer> dfs(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> queue = new Stack<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode e = queue.pop();

            if (e.right != null) {
                queue.push(e.right);
            }
            if (e.left != null) {
                queue.push(e.left);
            }
            res.add(e.value);
        }
        return res;
    }
}
