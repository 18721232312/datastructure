package com.albk.datastructure.base.tree;

/**
 * @author BK
 * @version V2.0
 * @description: 二叉搜索树
 * @team: ALBK
 * @date 2018/4/22 12:33
 */
public class BinarySearchTree {

    private TreeNode<Integer> root;

    /**
     * 构造一颗搜索二叉树
     * @param value
     */
    public void put(int value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return;
        }
        TreeNode<Integer> parent = null;
        TreeNode<Integer> searchNode = root;
        while (searchNode != null) {
            parent = searchNode;
            if (value < searchNode.value) {
                searchNode = searchNode.left;
            } else {
                searchNode = searchNode.right;
            }
        }
        TreeNode<Integer> node = new TreeNode<>(value);
        if (value < parent.value) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public void inOrder(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.value);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.put(50);
        tree.put(35);
        tree.put(99);
        tree.put(45);
        tree.put(14);
        tree.put(88);
        tree.put(77);
        tree.put(5);
        tree.put(20);
        tree.put(108);
        tree.put(53);
        tree.inOrder(tree.root);
    }

}
