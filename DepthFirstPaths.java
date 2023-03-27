import java.util.Stack;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class DepthFirstPaths<Item> {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private final int l;
    private int quantity = 0;
    private boolean flag = false;
    private static final String testFile = "test1.txt";

    public DepthFirstPaths(Graph G, int s, int l) {
        this.s = s;
        this.l =l;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        if(flag) return;
        ++quantity;
        for (int w : G.adj(v)) {
            if(w==l) {
                marked[w] = true;
                flag = true;
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        In in = new In(testFile);
        int s=Integer.parseInt("0");
        Graph G = new Graph(in);
        int l= StdRandom.uniform(G.V() - 1);
        @SuppressWarnings({ "rawtypes", "unused" })
        DepthFirstPaths dfs = new DepthFirstPaths(G, s, l);
    }
}