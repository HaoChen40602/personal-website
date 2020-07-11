import java.util.*;
import java.io.*;

public class piggyback {

    public static int n;
    public static ArrayList[] g;

    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("piggyback.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int bessie = Integer.parseInt(s.nextToken());
        int elsie = Integer.parseInt(s.nextToken());
        int piggy = Integer.parseInt(s.nextToken());
        n = Integer.parseInt(s.nextToken());
        g = new ArrayList[n];
        for (int i=0; i<n; i++){
            g[i] = new ArrayList<Integer>();
        }
        int numE = Integer.parseInt(s.nextToken());
        for (int i = 0; i < numE; i++) {
            s = new StringTokenizer(f.readLine());
            int v1 = Integer.parseInt(s.nextToken()) - 1;
            int v2 = Integer.parseInt(s.nextToken()) - 1;
            g[v1].add(v2);
            g[v2].add(v1);
        }
        int[] bDist = bfs(0);
        int[] eDist = bfs(1);
        int[] dDist = bfs(n - 1);

        int res = bessie * bDist[n - 1] + elsie * eDist[n - 1];
        
        for(int mid = 0; mid < n - 1; mid++){
            if(bDist[mid] == -1 || eDist[mid] == -1 || dDist[mid] == -1){
                continue;
            }
            
            res = Math.min(res, bessie * bDist[mid] + elsie * eDist[mid] + piggy * dDist[mid]);
        }
        PrintWriter out = new PrintWriter(new FileWriter("piggyback.out"));
        out.println(res);
        out.close();
        f.close();
    }
    
    public static int[] bfs(int v){
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[v] = 0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(v);
        while (q.size() > 0) {
            int cur = q.poll();
            for (int next: (ArrayList<Integer>) g[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
        return dist;
    }
}