import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Degrees {
    private Digraph d;

    Degrees(Digraph GRAPH){
        this.d = GRAPH;
    }
    int indegree(int v){
        int count = 0;
        for(int i=0; i<d.V(); i++){
            for(int j: d.adj(i))
                if(j==v) count++;
        }
        return count;
    }
    int outdegree(int v){
        int count =0;
        for(int a: d.adj(v)){
            count++;
        }
        return count;
    }
    Iterable<Integer> sources() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < d.V(); i++) {
            if (indegree(i) == 0)
                stack.push(i);
        }
            return stack;
    }

    Iterable<Integer> sinks() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < d.V(); i++) {
            for (int a : d.adj(i)) {
                if (outdegree(a) == 0) stack.push(a);
            }
        }
        return stack;
    }
    boolean isMap() {
        for (int i = 0; i < d.V(); i++) {
            for (int a : d.adj(i)) {
                if (outdegree(a) != 1) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(new In("digraph2.txt"));
        System.out.println(g.toString());
        Degrees d = new Degrees(g);
        System.out.println("souces:");
        for (int v : d.sources())
            System.out.print(v + " ");
        System.out.println();

        System.out.println();
        System.out.println("sinks:");
        for (int v : d.sinks())
            System.out.print(v + " ");
        System.out.println();

        for (int i = 0; i < g.V(); i++) {
            System.out.println(i + " ->   indegree  = " + d.indegree(i) + ", outdegree = " + d.outdegree(i));
        }
        System.out.println("digraph2 isMap - " + d.isMap());
    }
}
