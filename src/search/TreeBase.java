package search;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * 四种遍历方式
 */
public class TreeBase {

    public class Node {

        public Node(String val) {
            this.val = val;
        }

        public String val;
        private Node left;
        private Node right;
    }

    /**
     * 先序遍历
     */
    public static void preNode(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preNode(root.left);
        preNode(root.right);
    }

    /**
     * 中序遍历
     */
    public static void midNode(Node root) {
        if (root == null) {
            return;
        }
        midNode(root.left);
        System.out.print(root.val + " ");
        midNode(root.right);
    }

    /**
     * 后序遍历
     */
    public static void endNode(Node root) {
        if (root == null) {
            return;
        }
        endNode(root.left);
        endNode(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 按层遍历
     */
    public static void layerNodeFirst(Node root, Queue<Node> queue) {
        if (root == null) {
            return;
        }
        queue.add(root);
        layerNode(root, queue);
    }

    private static void layerNode(Node root, Queue<Node> queue) {
        if (root == null) {
            return;
        }
        System.out.print(Objects.requireNonNull(queue.poll()).val + " ");
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
        layerNode(root.left, queue);
        layerNode(root.right, queue);
    }

    public Node initNode() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        a.left = b;
        a.right = c;

        Node d = new Node("D");
        Node e = new Node("E");
        b.left = d;
        b.right = e;

        Node f = new Node("F");
        Node g = new Node("G");
        c.left = f;
        c.right = g;

        return a;
    }

    public static void main(String[] args) {
        TreeBase tree = new TreeBase();
        Node root = tree.initNode();
        System.out.println("先序遍历：");
        preNode(root);
        System.out.println();
        System.out.println("中序遍历：");
        midNode(root);
        System.out.println();
        System.out.println("后序遍历：");
        endNode(root);
        System.out.println();
        System.out.println("按层遍历：");
        layerNodeFirst(root, new ArrayDeque<>());
    }
}
