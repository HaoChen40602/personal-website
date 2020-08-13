import java.util.*;
import java.io.*;
public class superbull{
    public static void main(String[] args) throws Exception {
        
        BufferedReader f = new BufferedReader(new FileReader("superbull.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        int N = Integer.parseInt(f.readLine());
        int[] nodes = new int[N];
        for(int i = 0; i < N; i++){
            nodes[i] = Integer.parseInt(f.readLine());
        }
        ArrayList<Edge>[] g = new ArrayList[N];
        for(int i = 0; i < N; i++){
            g[i] = new ArrayList<Edge>();
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i != j){
                    g[i].add(new Edge(j, nodes[i] ^ nodes[j]));
                }
            }
        }
        long ans = prim(g, N);
        PrintWriter out = new PrintWriter(new FileWriter("superbull.out"));
        out.println(ans);
        out.close();
    }
    
    public static long prim(ArrayList<Edge>[] g, int N){
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        HashSet<Integer> set = new HashSet<Integer>();
        
        for(int i = 1; i < N; i++){
            set.add(i);
        }
        for(Edge e : g[0]){
            q.offer(e);
        }
        
        long ans = 0;
        while(set.isEmpty() == false){
            Edge current = q.poll();
            if(set.contains(current.v)){
                set.remove(current.v);
                ans += current.w;
                for(Edge e : g[current.v]){
                    if(set.contains(e.v)){
                        q.add(new Edge(e.v, e.w));
                    }
                }
            }
        }
        return ans;
    }
}

class Edge implements Comparable<Edge>{
    public int v;
    public int w;
    public Edge(int myv, int myw) {
        v = myv;
        w = myw;
    }
    
    public int compareTo(Edge other){
        return other.w - this.w;
    }
}
