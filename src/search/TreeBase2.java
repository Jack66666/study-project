package search;

import java.util.*;

/**
 * 二叉查找树要求，在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值。
 */
public class TreeBase2 {

    public class Node {

        public Node(int val) {
            this.val = val;
        }

        public int val;
        private Node left;
        private Node right;
    }

    /**
     * 不支持相同值
     *
     * @param node
     * @param val
     * @return
     */
    private static Node search(Node node, int val) {
        while (node != null) {
            if (node.val == val) {
                return node;
            } else if (node.val > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    /**
     * 不支持相同值
     *
     * @param node
     * @param val
     * @return
     */
    private void insert(Node node, int val) {
        Node addNode = new Node(val);
        while (node != null) {
            if (node.val > val) {
                if (node.left == null) {
                    node.left = addNode;
                    return;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = addNode;
                    return;
                }
                node = node.right;
            }
        }
    }

    /**
     * 不支持相同值
     *
     * @param node
     * @param val
     * @return
     */
    private static void delete(Node node, int val) {
        Node pp = node;
        Node n = null;
        while (node != null) {
            if (node.val == val) {
                n = node;
                break;
            } else if (node.val > val) {
                pp = node;
                node = node.left;
            } else {
                pp = node;
                node = node.right;
            }
        }
        if (n == null) {
            return;
        }
        if (n.left != null && n.right != null) {
            Node minpp = n;
            Node min = n.right;
            while (min.left != null) {
                minpp = min;
                min = min.left;
            }
            n.val = min.val;
            pp = minpp;
            n = min;
        }

        if (pp.left == n) {
            if (n.left != null) {
                pp.left = n.left;
            } else if (n.right != null) {
                pp.left = n.right;
            } else {
                pp.left = null;
            }
        } else if (pp.right == n) {
            if (n.left != null) {
                pp.right = n.left;
            } else if (n.right != null) {
                pp.right = n.right;
            } else {
                pp.right = null;
            }
        }
    }

    /**
     * 最小值
     * @param node
     * @return
     */
    private static Node minNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 最大值
     * @param node
     * @return
     */
    private static Node maxNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 前驱结点
     * @param node
     * @param val
     * @return
     */
    private static Node preNode(Node node, int val) {
        if (node == null) {
            return node;
        }
        return maxNode(node.left);
    }

    /**
     * 后继节点
     * @param node
     * @param val
     * @return
     */
    private static Node lastNode(Node node, int val) {
        if (node == null) {
            return node;
        }
        return minNode(node.right);
    }

    public static void main(String[] args) {
        TreeBase2 treeBase2 = new TreeBase2();
        Node node = treeBase2.new Node(13);

        Node node8 = treeBase2.new Node(8);
        Node node18 = treeBase2.new Node(18);
        node.left = node8;
        node.right = node18;

        Node node6 = treeBase2.new Node(6);
        Node node10 = treeBase2.new Node(10);
        node8.left = node6;
        node8.right = node10;

        Node node16 = treeBase2.new Node(16);
        Node node20 = treeBase2.new Node(20);
        node18.left = node16;
        node18.right = node20;

        System.out.println(search(node, 16).val);
//        treeBase2.insert(node, 9);
//        treeBase2.insert(node, 11);
        System.out.println(111);
        delete(node, 13);
        System.out.println(222);
//        delete(node, 10);
//        System.out.println(333);
//        delete(node, 11);
//        System.out.println(444);
//        delete(node, 9);
//        System.out.println(555);
//        System.out.println(minNode(node).val);
//        System.out.println(maxNode(node).val);
//        System.out.println(preNode(node, 13).val);
//        System.out.println(lastNode(node, 13).val);
    }
}
