import java.io.*;
import java.util.*;
public class lasers{
    public static void main(String[] args) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int Lx = Integer.parseInt(s.nextToken());
        int Ly = Integer.parseInt(s.nextToken());
        int Bx = Integer.parseInt(s.nextToken());
        int By = Integer.parseInt(s.nextToken());
        int[][] pots = new int[N + 2][2];
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            pots[i + 1][0] = Integer.parseInt(s.nextToken());
            pots[i + 1][1] = Integer.parseInt(s.nextToken());
        }
        pots[0][0] = Lx;
        pots[0][1] = Ly;
        pots[N + 1][0] = Bx;
        pots[N + 1][1] = By;
        N += 2;
        ArrayList<int[]>[] g = new ArrayList[N];
        for(int i = 0; i < N; i++){
            g[i] = new ArrayList<int[]>();
        }
        HashMap<Integer, ArrayList<Integer>> xMap = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> yMap = new HashMap<Integer, ArrayList<Integer>>();
        
        for(int i = 0; i < N; i++){
            if(xMap.containsKey(pots[i][0])){
                xMap.get(pots[i][0]).add(i);
            }else{
                xMap.put(pots[i][0], new ArrayList<Integer>());
                xMap.get(pots[i][0]).add(i);
            }
            if(yMap.containsKey(pots[i][1])){
                yMap.get(pots[i][1]).add(i);
            }else{
                yMap.put(pots[i][1], new ArrayList<Integer>());
                yMap.get(pots[i][1]).add(i);
            }
        }
        for(int key : xMap.keySet()){
            ArrayList<Integer> current = xMap.get(key);
            Collections.sort(current);
            for(int i = 0; i < current.size() - 1; i++){
                g[current.get(i)].add(new int[]{current.get(i + 1), 0});
            }
            for(int i = current.size() - 1; i > 0; i--){
                g[current.get(i)].add(new int[]{current.get(i - 1), 2});
            }
        }
        for(int key : yMap.keySet()){
            ArrayList<Integer> current = yMap.get(key);
            Collections.sort(current);
            for(int i = 0; i < current.size() - 1; i++){
                g[current.get(i)].add(new int[]{current.get(i + 1), 1});
            }
            for(int i = current.size() - 1; i > 0; i--){
                g[current.get(i)].add(new int[]{current.get(i - 1), 3});
            }
        }
        pw.println(dijkstras(g));
        pw.close();
    }
    
    public static int dijkstras(ArrayList<int[]>[] g) {
        int[][] dist = new int[g.length][4];
        for(int i = 0; i < dist.length; i++){
            Arrays.fill(dist[i], -1);
        }
        Arrays.fill(dist[0], -1);
        
        PriorityQueue<edge> pq = new PriorityQueue<edge>();
        for (int[] e: g[0]){
            pq.offer(new edge(e[0], 0, e[1]));
        }
        
        while (pq.size() > 0){
            edge cur = pq.poll();
            if (dist[cur.v][cur.dir] != -1){
                continue;
            }
            dist[cur.v][cur.dir] = cur.w;
            if(cur.v == g.length - 1){
                return cur.w;
            }
            for (int[] e: g[cur.v]){
                if (dist[e[0]][e[1]] == -1){
                    if(cur.dir == e[1]){
                        pq.offer(new edge(e[0], cur.w, e[1]));
                    }else{
                        pq.offer(new edge(e[0], cur.w + 1, e[1]));
                    }
                }
            }
        }
        return -1;
    }
}

class edge implements Comparable<edge> {
    public int v;
    public int w;
    public int dir;
    public edge(int to, int weight, int d) {
        v = to;
        w = weight;
        dir = d;
    }
    public int compareTo(edge other) {
        return w - other.w;
    }   
}