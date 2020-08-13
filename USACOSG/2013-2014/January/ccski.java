import java.util.*;
import java.io.*;
public class ccski{
    static int M;
    static int N;
    static int[][] ele;
    static ArrayList<int[]> points;
    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("ccski.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        M = Integer.parseInt(s.nextToken());
        N = Integer.parseInt(s.nextToken());
        ele = new int[M][N];
        points = new ArrayList<int[]>();
        for(int i = 0; i < M; i++){
            s = new StringTokenizer(f.readLine());
            for(int j = 0; j < N; j++){
                ele[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        for(int i = 0; i < M; i++){
            s = new StringTokenizer(f.readLine());
            for(int j = 0; j < N; j++){
                int a = Integer.parseInt(s.nextToken());
                if(a == 1){
                    points.add(new int[]{i, j});
                }
            }
        }
        PrintWriter out = new PrintWriter(new File("ccski.out"));
        out.println(bSearch());
        out.close();
    }
    
    public static int bSearch(){
        int hi = 1000000000;
        int lo = -1;
        while (hi > lo + 1) {
            int mid = (lo + hi) / 2;
            if(verify(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
    
    public static boolean verify(int max){
        LinkedList<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[M][N];
        queue.add(points.get(0));
        visited[points.get(0)[0]][points.get(0)[1]] = true;
        while(queue.isEmpty() == false){
            int[] current = queue.poll();
            for(int k = 0; k < 4; k++){
                int cx = current[0] + moves[k][0];
                int cy = current[1] + moves[k][1];
                if(cx < 0 || cx >= M || cy < 0 || cy >= N){
                    continue;
                }
                if(visited[cx][cy] == false && Math.abs(ele[cx][cy] - ele[current[0]][current[1]]) <= max){
                    visited[cx][cy] = true;
                    queue.add(new int[]{cx, cy});
                }
            }
        }
        for(int[] i : points){
            if(visited[i[0]][i[1]] == false){
                return false;
            }
        }
        return true;
    }
} 
