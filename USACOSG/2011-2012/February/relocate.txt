import java.util.*;
import java.io.*;
public class Relocation{
    public static int n;
    public static LinkedList[] g;
    static ArrayList<int[]> list;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("relocate.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        n = Integer.parseInt(s.nextToken());
        int C = Integer.parseInt(s.nextToken());
        int K = Integer.parseInt(s.nextToken());
        int[] sources = new int[K];
        g = new LinkedList[n];
        HashSet<Integer> set = new HashSet<Integer>();
        list = new ArrayList<int[]>();
        
        int[] ad = new int[K];
        for(int i = 0; i < K; i++){
            ad[i] = i;
        }
        permute(ad, 0, ad.length - 1);
        for (int i = 0; i < n; i++){
            g[i] = new LinkedList<edge>();
        }
        
        for(int i = 0; i < K; i++){
            sources[i] = Integer.parseInt(f.readLine()) - 1;
            set.add(sources[i]);
        }
        
        for(int i = 0; i < C; i++){
            s = new StringTokenizer(f.readLine());
            int p1 = Integer.parseInt(s.nextToken()) - 1;
            int p2 = Integer.parseInt(s.nextToken()) - 1;
            int cost = Integer.parseInt(s.nextToken());
            g[p1].add(new edge(p2, cost));
            g[p2].add(new edge(p1, cost));
        }
        
        int[][] dist = new int[K][];
        for(int i = 0; i < K; i++){
            dist[i] = dijkstras(sources[i]);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(set.contains(i)){
                continue;
            }
            for(int[] arr : list){
                int current = 0;
                int previous = i;
                for(int j : arr){
                    current += dist[j][previous];
                    previous = sources[j];
                }
                current += dist[arr[arr.length - 1]][i];
                min = Math.min(min, current);
            }
        }
        System.out.println(min);
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
    
    public static void permute(int[] arr, int l, int r){ 
        if(l == r){
            list.add(Arrays.copyOf(arr, arr.length));
        }else{
            for(int i = l; i <= r; i++){ 
                int temp = arr[l];
                arr[l] = arr[i];
                arr[i] = temp;
                permute(arr, l + 1, r);
                temp = arr[l];
                arr[l] = arr[i];
                arr[i] = temp;
            } 
        } 
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