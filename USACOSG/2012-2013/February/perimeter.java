import java.util.*;
import java.io.*;
public class perimeter{
    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] arr;
    static boolean[][] matrix;
    public static void main(String[] args) throws Exception{
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        PrintWriter out = new PrintWriter(new File("perimeter.out"));
        //BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
        int N = Integer.parseInt(f.readLine());
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        arr = new int[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(s.nextToken());
            int y = Integer.parseInt(s.nextToken());
            minX = Math.min(minX, x - 1);
            minY = Math.min(minY, y - 1);
            maxX = Math.max(maxX, x + 1);
            maxY = Math.max(maxY, y + 1);
            arr[i][0] = x;
            arr[i][1] = y;
        }
        maxX -= minX;
        maxY -= minY;
        matrix = new boolean[maxX + 1][maxY + 1];
        for(int i = 0; i < N; i++){
            arr[i][0] -= minX;
            arr[i][1] -= minY;
            matrix[arr[i][0]][arr[i][1]] = true;
        }
        int ans = floodfill();
        System.out.println(ans);
        out.close();
    }
    
    public static int floodfill(){
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0});
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[0][0] = true;
        int perimeter = 0;
        while(queue.isEmpty() == false){
            int[] current = queue.poll();
            for(int k = 0; k < 4; k++){
                int cx = current[0] + moves[k][0];
                int cy = current[1] + moves[k][1];
                if(cx < 0 || cx >= matrix.length || cy < 0 || cy >= matrix[0].length){
                    continue;
                }
                if(matrix[cx][cy]){
                    perimeter++;
                }
                if(visited[cx][cy] == false && matrix[cx][cy] == false){
                    visited[cx][cy] = true;
                    queue.add(new int[]{cx, cy});
                }
            }
        }
        return perimeter;
    }
    
}
