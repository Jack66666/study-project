package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SkipList {

    public int MAX_LEVEL = 16;
    private int levelCount = 1;

    public Node head;

    {
        head = new Node(MAX_LEVEL);
    }

    private Random r = new Random();

    public class Node {
        public int data = -1;
        public Node[] forwards;

        public Node(int level) {
            forwards = new Node[level];
        }
    }

    public int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void insert(int value) {
        int level = head.forwards[0] == null ? 1 : randomLevel();
        // 每次只增加一层，如果条件满足
        if (level > levelCount) {
            level = ++levelCount;
        }
        Node newNode = new Node(level);
        newNode.data = value;
        Node p = head;
        // 必须从大到小（从小到大会出现数组越界，即p.forwards[i]，从大到小已经越过了过大的数组下标，直接指向下一坐标节点，所以不会越界）从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
//        for (int i = 0; i < levelCount  ; i--) {
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }
            // levelCount 会 > level（randomLevel()后level可能小于levelCount），所以加上判断
            if (level > i) {
                if (p.forwards[i] == null) {
                    p.forwards[i] = newNode;
                } else {
                    newNode.forwards[i] = p.forwards[i];
                    p.forwards[i] = newNode;
                }
            }
        }
    }

    public int find(int value) {
        System.out.println("查询路径：" + value);
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
                System.out.print(p.data + ":" + i + "--");
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            System.out.print(p.forwards[0].data + ":" + 0 + "--");
            return p.forwards[0].data;
        } else {
            return -1;
        }
    }

    public void delete(int value) {
        System.out.println("删除节点：" + value);
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }
            if (p.forwards[i] != null && p.forwards[i].data == value) {
                p.forwards[i] = p.forwards[i].forwards[i];
            }
        }
    }

    public void printFormat() {
        System.out.println("跳表格式输出：");
        Node tmp = this.head.forwards[0];
        List<Integer> vals = new ArrayList<>();
        while (tmp != null) {
            vals.add(tmp.data);
            tmp = tmp.forwards[0];
        }
        for (int i = 16 - 1; i >= 0; i--) {
            int space = 3;
            Node hn = this.head;
            int idxLast = -1;
            while (hn.forwards[i] != null) {
                int idx = getIndex(vals, hn.forwards[i].data);
                for (int j = 0; j < idx - idxLast; j++) {
                    if (idx - idxLast - j == 1) {
                        System.out.print("----");
                    } else {
                        System.out.print("----");
                        for (int s = 0; s < space; s++) System.out.print(" ");
                    }
                }
                System.out.print(hn.forwards[i].data + ":" + i);
                hn = hn.forwards[i];
                idxLast = idx;
            }
            if (hn != this.head) {
                System.out.println();
            }
        }
    }

    private int getIndex(List<Integer> vals, int value) {
        for (int i = 0; i < vals.size(); i++) {
            if (vals.get(i) == value) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(3);
        skipList.insert(9);
        skipList.insert(8);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(4);
        skipList.insert(5);
        skipList.insert(10);
//        skipList.insert(11);
//        skipList.insert(12);
        skipList.printFormat();
        System.out.println("result:" + skipList.find(8));
        skipList.delete(7);
        skipList.printFormat();
    }
}
