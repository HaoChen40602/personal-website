import java.util.*;
import java.io.*;
public class tractor{
    static int N;
    static int[][] matrix;
    static int[][] fill;
    static int[][] moves = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    public static void main(String[] args) throws Exception{
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("tractor.in"));
        PrintWriter out = new PrintWriter(new File("tractor.out"));
        N = Integer.parseInt(f.readLine());
        matrix = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            for(int j = 0; j < N; j++){
                matrix[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        int lo = -1;
        int hi = 1000001;
        while (hi > lo + 1) {
            int mid = (lo + hi) / 2;
            if(verify(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        out.println(hi);
        out.close();
    }
    
    
    public static boolean verify(int max){
        fill = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                fill[i][j] = -1;
            }
        }
        int id = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(fill[i][j] == -1){
                    id++;
                    if(floodfill(i, j, id, max) * 2 >= N * N){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static int floodfill(int i, int j, int id, int max){
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{i, j});
        fill[i][j] = id;
        int area = 1;
        while(queue.isEmpty() == false){
            int[] current = queue.poll();
            for(int k = 0; k < 4; k++){
                int cx = current[0] + moves[k][0];
                int cy = current[1] + moves[k][1];
                if(cx < 0 || cx >= N || cy < 0 || cy >= N){
                    continue;
                }
                if(fill[cx][cy] == -1 && Math.abs(matrix[cx][cy] - matrix[current[0]][current[1]]) <= max){
                    fill[cx][cy] = id;
                    area++;
                    queue.add(new int[]{cx, cy});
                }
            }
        }
        return area;
    }
}