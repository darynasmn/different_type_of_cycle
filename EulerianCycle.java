import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

import java.util.Stack;


public class EulerianCycle {

    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        public int other(int vertex) {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("No such a vertex");
        }
    }

    private  Stack<Integer> cycle = new Stack<Integer>();

    public EulerianCycle(Graph G) {

        if (G.E() == 0) return;

        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 != 0)
                return;

        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = new Queue<Edge>();

        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (int w : G.adj(v)) {
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].enqueue(e);
                        adj[w].enqueue(e);
                    }
                    selfLoops++;
                } else if (v < w) {
                    Edge e = new Edge(v, w);
                    adj[v].enqueue(e);
                    adj[w].enqueue(e);
                }
            }
        }


        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].dequeue();
                if (edge.isUsed) continue;
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            cycle.push(v);
        }

        if (cycle.size() != G.E() + 1)
            cycle = null;
    }

    private static int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasEulerianCycle() {
        return cycle.size() != 0;
    }

    public static void main(String[] args) {
        Graph graph= new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(2,3);
        EulerianCycle ec = new EulerianCycle(graph);
        System.out.println(ec.hasEulerianCycle());
        for(int e: ec.cycle()){
            System.out.print(e + " - ");
        }
    }
}