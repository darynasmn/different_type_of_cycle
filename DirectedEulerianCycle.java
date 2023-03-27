import edu.princeton.cs.algs4.Digraph;

import java.util.Iterator;
import java.util.Stack;

public class DirectedEulerianCycle {
    private Stack<Integer> cycle = new Stack<Integer>();

    public DirectedEulerianCycle(Digraph G) {

        if (G.E() == 0) return;

        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) != G.indegree(v))
                return;

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();


        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }

            cycle.push(v);
        }

        if (cycle.size() != G.E() + 1)
            cycle = null;
    }

    private static int nonIsolatedVertex(Digraph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) > 0)
                return v;
        return -1;
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(5);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 0);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        DirectedEulerianCycle ec = new DirectedEulerianCycle(graph);
        System.out.println(ec.hasEulerianCycle());
        for(int e: ec.cycle()){
            System.out.print(e + " - ");
        }
    }

}