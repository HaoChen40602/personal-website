import java.util.*;
import java.io.*;
public class vacation{
    public static int n;
    public static LinkedList[] g;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("vacation.in"));
        PrintWriter out = new PrintWriter(new File("vacation.out"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        n = Integer.parseInt(s.nextToken());
        int M = Integer.parseInt(s.nextToken());
        int K = Integer.parseInt(s.nextToken());
        int Q = Integer.parseInt(s.nextToken());
        g = new LinkedList[n];
        
        for (int i = 0; i < n; i++){
            g[i] = new LinkedList<edge>();
        }
        
        for(int i = 0; i < M; i++){
            s = new StringTokenizer(f.readLine());
            int p1 = Integer.parseInt(s.nextToken()) - 1;
            int p2 = Integer.parseInt(s.nextToken()) - 1;
            int cost = Integer.parseInt(s.nextToken());
            g[p1].add(new edge(p2, cost));
        }
        
        int[][] dists = new int[n][];
        for(int i = 0; i < n; i++){
            dists[i] = dijkstras(i);
        }
        
        int numV = 0;
        long sum = 0;
        
        for(int i = 0; i < Q; i++){
            s = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(s.nextToken()) - 1;
            int b = Integer.parseInt(s.nextToken()) - 1;
            int ans = Integer.MAX_VALUE;
            
            for(int j = 0; j < K; j++){
                
                if(dists[a][j] != -1 && dists[j][b] != -1){
                    ans = Math.min(ans, dists[a][j] + dists[j][b]);
                }
            }
            
            if(ans != Integer.MAX_VALUE){
                numV++;
                sum += ans;
            }
        }
        out.println(numV);
        out.println(sum);
        out.close();
    }
    
    @SuppressWarnings("unchecked")
    public static int[] dijkstras(int source) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[source] = 0;
        PriorityQueue<edge> pq = new PriorityQueue<edge>();
        for (edge e: (LinkedList<edge>)g[source]){
            pq.offer(e);
        }
        while (pq.size() > 0){
            edge cur = pq.poll();
            
            if (dist[cur.v] != -1){
                continue;
            }
            
            dist[cur.v] = cur.w;
            
            for (edge e: (LinkedList<edge>) g[cur.v]){
                if (dist[e.v] == -1){
                    pq.offer(new edge(e.v, cur.w + e.w));
                }
            }
        }
        return dist;
    }
}

class edge implements Comparable<edge> {
    public int v;
    public int w;
    public edge(int to, int weight) {
        v = to;
        w = weight;
    }
    public int compareTo(edge other) {
        return w - other.w;
    }   
}