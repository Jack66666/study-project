package search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 */
public class Graph {

    /**
     * 顶点的个数
     */
    public int v;
    /**
     * 邻接表，值即为数组坐标，LinkedList为本顶点所关联的顶点
     */
    public LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先
     *
     * @param s
     * @param t
     */
    public void bfsSelf(int s, int t) {
        if (s == t) {
            return;
        }
        // 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。因为广度优先搜索是逐层访问的，
        // 也就是说，我们只有把第 k 层的顶点都访问完成之后，才能访问第 k+1 层的顶点。当我们访问到第 k 层的顶点的时候，
        // 我们需要把第 k 层的顶点记录下来，稍后才能通过第 k 层的顶点来找第 k+1 层的顶点。所以，我们用这个队列来实现记录的功能。
        Queue<Integer> queue = new LinkedList<>();
        // 用来记录搜索路径。当我们从顶点 s 开始，广度优先搜索到顶点 t 后，prev 数组中存储的就是搜索的路径。
        // 不过，这个路径是反向存储的。prev[w]存储的是，顶点 w 是从哪个前驱顶点遍历过来的。比如，我们通过顶点 2 的邻接表访问到顶点 3，那 prev[3]就等于 2。
        int[] path = new int[v];
        // 是用来记录已经被访问的顶点，用来避免顶点被重复访问
        int[] visit = new int[v];
        for (int i = 0; i < path.length; i++) {
            path[i] = -1;
        }

        queue.add(s);
        visit[s] = 1;
        int node = bfsSearch(queue, t, path, visit);

        print(path, node);
    }

    private int bfsSearch(Queue<Integer> queue, int t, int[] path, int[] visit) {
        Integer node = queue.poll();
        if (node == null) {
            return -1;
        }

        LinkedList<Integer> relateNode = adj[node];
        for (Integer n : relateNode) {
            if (n == t) {
                return node;
            }
            if (visit[n] != 0) {
                continue;
            }
            queue.add(n);
            visit[n] = 1;
            path[n] = node;
        }
        return bfsSearch(queue, t, path, visit);
    }

    private void print(int[] path, int node) {
        if (node < 0) {
            return;
        }
        if (path[node] != -1) {
            print(path, path[node]);
        }
        System.out.print(" " + node);
    }

    /**
     * 1、找到目标顶点后记录目标顶点父级位置，便于打印
     * 2、也代表不为-1即是找到目标顶点，从而推出递归
     */
    public int dfsStopNode = -1;

    /**
     * 深度优先
     *
     * @param s
     * @param t
     */
    public void dfsSelf(int s, int t) {
        if (s == t) {
            return;
        }
        // 与广度优先作用相同
        int[] path = new int[v];
        // 与广度优先作用相同
        int[] visit = new int[v];
        for (int i = 0; i < path.length; i++) {
            path[i] = -1;
        }
        visit[s] = 1;

        dfsSearch(s, t, path, visit);

        print(path, dfsStopNode);
    }

    private void dfsSearch(int s, int t, int[] path, int[] visit) {
        LinkedList<Integer> relateNode = adj[s];
        for (Integer n : relateNode) {
            if (n == t && dfsStopNode == -1) {
                dfsStopNode = s;
                return;
            }
            if (dfsStopNode != -1) {
                return;
            }
            if (visit[n] != 0) {
                continue;
            }
            visit[n] = 1;
            path[n] = s;
            // 若n的关联顶点都被访问或者没有关联顶点则不会再继续递归下去，那么就会回到父方法，也就相当于回溯了
            dfsSearch(n, t, path, visit);
        }
    }

    ///////////////////////////////////////////////作者代码///////////////////////////////////

    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
//        graph.bfsSelf(0, 6);

        graph.dfsSelf(0, 6);
    }
}
