// Arup Guha
// 5/11/2018
// Solution to USACO 2015 January Silver Problem: Cow Routing

import java.util.*;
import java.io.*;

public class cowroute {
    final public static int MAX = 1000;
    final public static long MAXD = 1000000000000000000L;
    public static int n;
    public static void main(String[] args) throws Exception {

        BufferedReader stdin = new BufferedReader(new FileReader("cowroute.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        int source = Integer.parseInt(tok.nextToken()) - 1;
        int dest = Integer.parseInt(tok.nextToken()) - 1;
        n = Integer.parseInt(tok.nextToken());
        
        ArrayList[] g = new ArrayList[MAX];
        for (int i = 0; i < MAX; i++){
            g[i] = new ArrayList<edge>();
        }
        
        for (int i=0; i<n; i++) {
            
            tok = new StringTokenizer(stdin.readLine());
            long w = Long.parseLong(tok.nextToken());
            int numE = Integer.parseInt(tok.nextToken());
            
            tok = new StringTokenizer(stdin.readLine());
            int[] route = new int[numE];
            for (int j = 0; j < numE; j++){
                route[j] = Integer.parseInt(tok.nextToken()) - 1;
            }
            for (int j = 0; j < numE; j++){
                for (int k = j + 1; k < numE; k++){
                    g[route[j]].add(new edge(route[k], 1000000L * w + k - j));
                }
            }
        }
        
        long res = dijkstra(g, source, dest);
        long dist = res > 0 ? res / 1000000L : -1;
        long numF = res > 0 ? res % 1000000L : -1;
        
        PrintWriter out = new PrintWriter(new FileWriter("cowroute.out"));
        out.println(dist+" "+numF);
        out.close();
        stdin.close();
    }

    public static long dijkstra(ArrayList[] g, int s, int e) {
        PriorityQueue<edge> pq = new PriorityQueue<edge>();
        long[] dist = new long[MAX];
        boolean[] used = new boolean[MAX];
        Arrays.fill(dist, MAXD);
        dist[s] = 0;
        pq.offer(new edge(s, 0));
        
        while (pq.size() > 0) {
            
            edge cur = pq.poll();
            if (used[cur.to]){
                continue;
            }
            used[cur.to] = true;
            if (cur.to == e){
                return cur.w;
            }
            
            for (edge next: (ArrayList<edge>)g[cur.to]) {
                if (dist[cur.to] + next.w < dist[next.to]) {
                    dist[next.to] = dist[cur.to] + next.w;
                    pq.offer(new edge(next.to, dist[next.to]));
                }
            }
        }
        
        return -1;
    }
}

class edge implements Comparable<edge>{
    public int to;
    public long w;
    public edge(int myv, long myw){
        to = myv;
        w = myw;
    }
    public int compareTo(edge other){
        if (this.w < other.w){
            return -1;
        }
        if (this.w > other.w){
            return 1;
        }
        return 0;
    }
}