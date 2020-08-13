import java.util.*;
import java.io.*;

public class irrigation {
    public static void main(String[] args) throws Exception {
        BufferedReader stdin = new BufferedReader(new FileReader("irrigation.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        int n = Integer.parseInt(tok.nextToken());
        int minE = Integer.parseInt(tok.nextToken());
        int[][] pts = new int[n][2];
        for (int i=0; i<n; i++) {
            tok = new StringTokenizer(stdin.readLine());
            pts[i][0] = Integer.parseInt(tok.nextToken());
            pts[i][1] = Integer.parseInt(tok.nextToken());
        }
        
        ArrayList[] g = new ArrayList[n];
        for (int i=0; i<n; i++){
            g[i] = new ArrayList<edge>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j<n; j++) {
                int dsqr = distsqr(pts[i],pts[j]);
                if (dsqr >= minE) {
                    g[i].add(new edge(i,j,dsqr));
                    g[j].add(new edge(j,i,dsqr));
                }
            }
        }
        
        Random r = new Random();
        PrintWriter out = new PrintWriter(new FileWriter("irrigation.out"));
        out.println(prims(g, r.nextInt(n)));
        out.close();
    }
    
    public static int distsqr(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
    
    public static int prims(ArrayList<edge>[] g, int v){
        int n = g.length;
        boolean[] used = new boolean[n];
        used[v] = true;
        PriorityQueue<edge> pq = new PriorityQueue<edge>();
        for (edge e: g[v]){
            pq.offer(e);
        }
        int numE = 0;
        int res = 0;
        
        while(pq.size() > 0 && numE < n - 1){
            edge cur = pq.poll();
            
            if(used[cur.u] && used[cur.v]){
                continue;
            }
            int newv = 0;
            if(used[cur.u]){
                newv = cur.v;
            }else{
                newv = cur.u;
            }
            used[newv] = true;
            res += cur.w;
            for(edge e: g[newv]){
                pq.offer(e);
            }
            numE++;
        }
        
        if(numE == n - 1){
            return res;
        }else{
            return -1;
        }
    }
}

class edge implements Comparable<edge> {
    public int u;
    public int v;
    public int w;
    public edge(int myu, int myv, int myw) {
        u = myu;
        v = myv;
        w = myw;
    }
    
    public int compareTo(edge other) {
        return w - other.w;
    }
}