import java.util.*;
import java.io.*;
public class radio{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("radio.in"));
        PrintWriter out = new PrintWriter(new File("radio.out"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int M = Integer.parseInt(s.nextToken());
        
        pair[] jPos = new pair[N + 1];
        pair[] bPos = new pair[M + 1];
        s = new StringTokenizer(f.readLine());
        int fx = Integer.parseInt(s.nextToken());
        int fy = Integer.parseInt(s.nextToken());
        jPos[0] = new pair(fx, fy);
        s = new StringTokenizer(f.readLine());
        fx = Integer.parseInt(s.nextToken());
        fy = Integer.parseInt(s.nextToken());
        bPos[0] = new pair(fx, fy);
        String str = f.readLine();
        for(int i = 0; i < N; i++){
            if(str.charAt(i) == 'N'){
                jPos[i + 1] = new pair(jPos[i].x, jPos[i].y + 1);
            }else if(str.charAt(i) == 'W'){
                jPos[i + 1] = new pair(jPos[i].x - 1, jPos[i].y);
            }else if(str.charAt(i) == 'S'){
                jPos[i + 1] = new pair(jPos[i].x, jPos[i].y - 1);
            }else{
                jPos[i + 1] = new pair(jPos[i].x + 1, jPos[i].y);
            }
        }
        str = f.readLine();
        for(int i = 0; i < M; i++){
            if(str.charAt(i) == 'N'){
                bPos[i + 1] = new pair(bPos[i].x, bPos[i].y + 1);
            }else if(str.charAt(i) == 'W'){
                bPos[i + 1] = new pair(bPos[i].x - 1, bPos[i].y);
            }else if(str.charAt(i) == 'S'){
                bPos[i + 1] = new pair(bPos[i].x, bPos[i].y - 1);
            }else{
                bPos[i + 1] = new pair(bPos[i].x + 1, bPos[i].y);
            }
        }
        
        int[][] dp = new int[N + 1][M + 1];
        dp[0][0] = 0;
        for(int i = 0; i < N + 1; i++){
            for(int j = 0; j < M + 1; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                dp[i][j] = 999999999;
                if(i - 1 >= 0){
                    dp[i][j] = Math.min(dp[i - 1][j] + getDis(jPos[i], bPos[j]), dp[i][j]);
                }
                if(j - 1 >= 0){
                    dp[i][j] = Math.min(dp[i][j - 1] + getDis(jPos[i], bPos[j]), dp[i][j]);
                }
                if(i - 1 >= 0 && j - 1 >= 0){
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + getDis(jPos[i], bPos[j]), dp[i][j]);
                }
            }
        }
        out.println(dp[N][M]);
        out.close();
    }
    
    public static int getDis(pair p1, pair p2){
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}

class pair{
    int x;
    int y;
    public pair(int x1, int y1){
        x = x1;
        y = y1;
    }
}