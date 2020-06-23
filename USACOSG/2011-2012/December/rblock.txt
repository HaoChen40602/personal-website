import java.util.*;
import java.io.*;

public class rblock {
    
    public static int n;
    public static ArrayList<edge>[] g;
    
    public static int[] dist;
    public static int[] path;
    
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("rblock.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        n = Integer.parseInt(s.nextToken());
        int numE = Integer.parseInt(s.nextToken());
        g = new ArrayList[n];
        
        for (int i = 0; i < n; i++){
            g[i] = new ArrayList<edge>();
        }
        
        for (int i = 0; i < numE; i++) {
            s = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(s.nextToken()) - 1;
            int v = Integer.parseInt(s.nextToken()) - 1;
            int w = Integer.parseInt(s.nextToken());
            g[u].add(new edge(u, v, w));
            g[v].add(new edge(v, u, w));
        }
        
        dijkstras(0, true);
        int best = dist[n - 1];
        int res = 0;
        
        int last = n - 1;
        while (path[last] != last) {
            int prev = path[last];
            adjustWeight(prev, last, true);
            dijkstras(0, false);
            res = Math.max(res, dist[n - 1] - best);
            adjustWeight(prev, last, false);
            last = path[last];
        }
        
        PrintWriter out = new PrintWriter(new FileWriter("rblock.out"));
        out.println(res);
        out.close();      
    }
    
    public static void adjustWeight(int start, int end, boolean doubleIt) {
        
        for(edge e: (ArrayList<edge>)g[start]){
            if (e.v == end){
                if(doubleIt){
                    e.w *= 2;
                }else{
                    e.w /= 2;
                }
            }
        }
        
        for(edge e: (ArrayList<edge>)g[end]){
            if (e.v == start){
                if(doubleIt){
                    e.w *= 2;
                }else{
                    e.w /= 2;
                }
            }
        }
    }
    
    public static void dijkstras(int source, boolean doPath) {
        dist = new int[n];
        Arrays.fill(dist, -1);
        dist[source] = 0;
        if (doPath) {
            path = new int[n];
            Arrays.fill(path, -1);
            path[source] = source;
        }
        
        PriorityQueue<edge> pq = new PriorityQueue<edge>();
        for (edge e: g[source]){
            pq.offer(e);
        }
        while (pq.size() > 0){
            edge cur = pq.poll();
            if (dist[cur.v] != -1){
                continue;
            }
            dist[cur.v] = cur.w;
            if (doPath){
                path[cur.v] = cur.u;
            }
            for (edge e: (ArrayList<edge>)g[cur.v]){
                if (dist[e.v] == -1){
                    pq.offer(new edge(e.u, e.v, cur.w + e.w));
                }
            }
        }
    }
}

class edge implements Comparable<edge> {

    public int u;
    public int v;
    public int w;
    
    public edge(int from, int to, int weight) {
        u = from;
        v = to;
        w = weight;
    }
    
    public int compareTo(edge other) {
        return w - other.w;
    }   
}