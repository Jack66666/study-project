package search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ac树为Trie树改进版本
 */
public class AcTree{
    public char data;
    public AcTree[] children;
    public boolean isEnd;
    public int length = -1;
    /**
     * 失败指针
     */
    public AcTree fail;

    public AcTree(char data) {
        this.data = data;
        children = new AcTree[26];
    }

    public void insert(char[] array, AcTree root) {
        int i = 0;
        for (; i < array.length; i++) {
            char data = array[i];
            int index = data - 'a';
            if (root.children[index] == null) {
                AcTree child = new AcTree(data);
                root.children[index] = child;
            }
            root = root.children[index];
        }
        root.isEnd = true;
        root.length = i;
    }

    /**
     * 构建失败指针，结果如图片：https://static001.geekbang.org/resource/image/51/3c/5150d176502dda4adfc63e9b2915b23c.jpg
     * @param root
     */
    public void buildFailPoint(AcTree root) {
        Queue<AcTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            AcTree p = queue.remove();
            for (int i = 0; i < 26; i++) {
                AcTree pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcTree q = p.fail;
                    while (q != null) {
                        if (q.children[pc.data - 'a'] != null) {
                            pc.fail = q.children[pc.data - 'a'];
                            break;
                        } else {
                            q = q.fail;
                        }
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    /**
     * 匹配
     * @param text
     * @param root
     */
    public void match(char[] text, AcTree root) {
        int length = text.length;
        AcTree p = root;
        for (int i = 0; i < length; i++) {
            while (p != null) {
                if (p.isEnd) {
                    break;
                }
                if (p.children[text[i] - 'a'] != null) {
                    p = p.children[text[i] - 'a'];
                    break;
                } else {
                    p = p.fail;
                }
            }
            AcTree tmp = p;
            // 根节点或是匹配字符最后一个节点则重新开始匹配
            if (p == null || p.isEnd) {
                p = root;
            }
            if (tmp != null && tmp.isEnd) {
                System.out.print("匹配串：");
                if (tmp.data != text[i]) {
                    int s = i - tmp.length;
                    for (; s < i; s++) {
                        System.out.print(text[s]);
                    }
                } else {
                    int s = i - tmp.length + 1;
                    for (; s < i + 1; s++) {
                        System.out.print(text[s]);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        AcTree acTree = new AcTree('/');
        char[] abce = new char[]{'a', 'b', 'c', 'd'};
        char[] bc = new char[]{'b', 'c'};
        char[] bcd = new char[]{'b', 'e', 'd'};
        char[] c = new char[]{'c'};
        acTree.insert(abce, acTree);
        acTree.insert(bc, acTree);
        acTree.insert(bcd, acTree);
        acTree.insert(c, acTree);
        acTree.buildFailPoint(acTree);

        char[] text = new char[]{'a', 'b', 'c', 'e', 'b', 'e', 'd'};
        acTree.match(text, acTree);
        System.out.println(acTree);
    }
}
