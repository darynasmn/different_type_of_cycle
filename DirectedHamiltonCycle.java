import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

public class DirectedHamiltonCycle {
    public static String cyclePrint="";
    public static Digraph G;
    public static final String testFile = "test2.txt";;
    public static int degreeArray[];
    private boolean[] marked;
    private Stack<Integer> reversePost;

    public DirectedHamiltonCycle(Digraph G){
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) dfs(G, w);
        reversePost.push(v);
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }

    boolean isHamilton(){
        int w=reversePost.pop();
        cyclePrint+=w+" -> ";
        while(!reversePost.isEmpty()){
            int v = reversePost.pop();
            cyclePrint+=v+" -> ";
            DirectedDepthFirstPaths dfs = new DirectedDepthFirstPaths(G, w,v);
            if (!dfs.hasPathTo(v)) {
                return false;
            }
            w=v;
        }
        cyclePrint+=0;
        return true;
    }

    public static void main(String[] args){
        In in = new In(testFile);
        G = new Digraph(in);
        DirectedHamiltonCycle hc = new DirectedHamiltonCycle(G);
        boolean flag= hc.isHamilton();
        System.out.println("DirectedHamiltonCycle: "+flag);
        if(flag)
            System.out.println(cyclePrint);
    }
}