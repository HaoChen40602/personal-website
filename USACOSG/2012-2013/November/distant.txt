import java.util.*;
import java.io.*;
public class distant{
    public static int n;
    public static LinkedList[] g;
    static ArrayList<int[]> list;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("distant.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        n = Integer.parseInt(s.nextToken());
        int A = Integer.parseInt(s.nextToken());
        int B = Integer.parseInt(s.nextToken());
        g = new LinkedList[n * n];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < n * n; i++){
            g[i] = new LinkedList<edge>();
        }
        
        boolean[][] matrix = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String str = f.readLine();
            for(int j = 0; j < n; j++){
                if(str.charAt(j) == '('){
                    matrix[i][j] = true;
                }else{
                    matrix[i][j] = false;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int a = 0; a < 4; a++){
                    int cx = i + dx[a];
                    int cy = j + dy[a];
                    if(cx >= 0 && cx < n && cy >= 0 && cy < n){
                        if(matrix[i][j] == matrix[cx][cy]){
                            g[i * n + j].add(new edge(cx * n + cy, A));
                        }else{
                            g[i * n + j].add(new edge(cx * n + cy, B));
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n * n; i++){
            int[] current = dijkstras(i);
            for(int k : current){
                ans = Math.max(ans, k);
            }
        }
        PrintWriter out = new PrintWriter(new File("distant.out"));
        out.println(ans);
        out.close();
    }
    
    @SuppressWarnings("unchecked")
    public static int[] dijkstras(int source) {
        int[] dist = new int[n * n];
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