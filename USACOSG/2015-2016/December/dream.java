import java.util.*;
import java.io.*;
public class dream{
    final public static int[] DX ={-1,0,0,1};
    final public static int[] DY ={0,-1,1,0};
    public static int r;
    public static int c;
    public static int[][] grid;
    public static int[] dist;
    public static void main(String[] args) throws Exception{
        BufferedReader stdin = new BufferedReader(new FileReader("dream.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        r = Integer.parseInt(tok.nextToken());
        c = Integer.parseInt(tok.nextToken());
        grid = new int[r][c];
        for (int i = 0; i < r; i++){
            tok = new StringTokenizer(stdin.readLine());
            for (int j = 0; j < c; j++){
                grid[i][j] = Integer.parseInt(tok.nextToken());
            }
        }
        PrintWriter out = new PrintWriter(new FileWriter("dream.out"));
	out.println(bfs());
	out.close();
        stdin.close();
    }
    
    public static int bfs(){
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.offer(2);
        q.offer(3);
        dist = new int[r * c * 8];
        Arrays.fill(dist, -1);
        dist[2] = 0;
        dist[3] = 0;
        
        while (q.size() > 0){
            int cur = q.poll();
            int dir = (cur & 3);
            int smell = (cur & 4);
            int loc = cur >> 3;
            int curX = loc / c;
            int curY = loc % c;
            if (loc == r * c - 1){
                return dist[cur];
            }
            int nextX = curX + DX[dir];
            int nextY = curY + DY[dir];
            
            if (!inbounds(nextX, nextY)){
                continue;
            }
            
            if (grid[nextX][nextY] == 0){
                continue;
            }
            
            if (grid[nextX][nextY] == 3 && smell != 4){
                continue;
            }
            
            if (grid[nextX][nextY] < 4){
                int thisSmell = 0;
                if(grid[nextX][nextY] == 2){
                    thisSmell = 4;
                }else{
                    thisSmell = smell;
                }
                for(int dirIndex = 0; dirIndex < 4; dirIndex++){
                    int index = 8 * (nextX * c + nextY) + thisSmell + dirIndex;
                    if(dist[index] == -1){
                        q.offer(index);
                        dist[index] = dist[cur] + 1;
                    }
                }
            }
            
            if (grid[nextX][nextY] == 4){
                if(bad(nextX, nextY, dir)){
                    for (int i = 0; i < 4; i++){
                        if (i == dir){
                            continue;
                        }
                        int index = 8 * (nextX * c + nextY) + i;
                        if (dist[index] == -1){
                            q.offer(index);
                            dist[index] = dist[cur] + 1;
                        }
                    }
                }else{
                    if (dist[8 * (nextX * c + nextY) + dir] == -1){
                        q.offer(8 * (nextX * c + nextY) + dir);
                        dist[8 * (nextX * c + nextY) + dir] = dist[cur] + 1;
                    }
                }
            }           
        }
        return -1;
    }
    
    public static boolean bad(int x, int y, int dir){
        int nextX = x + DX[dir];
        int nextY = y + DY[dir];
        if (!inbounds(nextX, nextY)){
            return true;
        }
        
        if (grid[nextX][nextY] == 0 || grid[nextX][nextY] == 3){
            return true;
        }
        
        return false;
    }
    public static boolean inbounds(int x, int y){
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}