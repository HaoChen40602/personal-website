import java.util.*;
import java.io.*;
public class mroute{
    public static int n;
    public static LinkedList[] g;
    public static int[] dist;
    public static int X;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("mroute.in"));
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        n = Integer.parseInt(s.nextToken());
        int M = Integer.parseInt(s.nextToken());
        X = Integer.parseInt(s.nextToken());
        
        g = new LinkedList[n];
        for (int i = 0; i < n; i++){
            g[i] = new LinkedList<edge>();
        }
        for(int i = 0; i < M; i++){
            s = new StringTokenizer(f.readLine());
            int p1 = Integer.parseInt(s.nextToken()) - 1;
            int p2 = Integer.parseInt(s.nextToken()) - 1;
            int cost = Integer.parseInt(s.nextToken());
            int capacity = Integer.parseInt(s.nextToken());
            g[p1].add(new edge(p2, cost, capacity));
            g[p2].add(new edge(p1, cost, capacity));
        }
        dijkstras(0);
        PrintWriter out = new PrintWriter(new File("mroute.out"));
        out.println(dist[n - 1]);
        out.close();
    }
    
    @SuppressWarnings("unchecked")
    public static void dijkstras(int source) {
        dist = new int[n];
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
            dist[cur.v] = cur.getTime();
            for (edge e: (LinkedList<edge>) g[cur.v]){
                if (dist[e.v] == -1){
                    pq.offer(new edge(e.v, cur.w + e.w, Math.min(cur.cap, e.cap)));
                }
            }
        }
    }
    
    static class edge implements Comparable<edge> {
        public int v;
        public int w;
        public int cap;
        public edge(int to, int weight, int capacity) {
            v = to;
            w = weight;
            cap = capacity;
        }
        public int getTime(){
            double a = this.w + 1.0 * X / this.cap;
            return (int) a;
        }
        public int compareTo(edge other) {
            Double a = this.w + 1.0 * X / this.cap;
            Double b = other.w + 1.0 * X / other.cap;
            return a.compareTo(b);
        }   
    }
}