import java.util.*;
import java.io.*;
public class moocast{
    static double[][] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        int n = Integer.parseInt(f.readLine());
        int[][] point = new int[n][2];
        for(int i = 0; i < n; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            point[i][0] = Integer.parseInt(s.nextToken());
            point[i][1] = Integer.parseInt(s.nextToken());
        }
        graph = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                graph[i][j] = getDist(point[i], point[j]);
            }
        }
        PrintWriter out = new PrintWriter(new File("moocast.out"));
        out.println(bSearch());
        out.close();
    }
    
    public static int bSearch(){
        int hi = Integer.MAX_VALUE;
        int lo = -1;
        while (hi > lo + 1) {
            int mid = (lo + hi) / 2;
            if(verify(Math.sqrt(mid))) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
    
    public static boolean verify(double max){
        boolean[] visited = new boolean[graph.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        visited[0] = true;
        int num = 1;
        while(queue.isEmpty() == false){
            int current = queue.poll();
            for(int i = 0; i < graph.length; i++){
                if(graph[current][i] <= max && visited[i] == false){
                    visited[i] = true;
                    num++;
                    queue.add(i);
                }
            }
        }
        if(num == graph.length){
           return true;
        }
        return false;
    }
    
    public static double getDist(int[] a, int[] b){
        return Math.sqrt((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]));
    }
} 
