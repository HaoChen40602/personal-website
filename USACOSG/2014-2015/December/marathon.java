import java.io.*;
import java.util.*;
import java.lang.Math;
public class marathon{
  public static void main (String [] args) throws IOException {
    //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
    BufferedReader f = new BufferedReader(new FileReader("marathon.in"));
    StringTokenizer s = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(s.nextToken());
    int K = Integer.parseInt(s.nextToken());
    pair[] checks = new pair[N + 1];
    for(int i = 0; i < N; i++){
        s = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(s.nextToken());
        int b = Integer.parseInt(s.nextToken());
        checks[i + 1] = new pair(a, b);
    }
    
    int[][] dp = new int[N + 1][K + 1];
    
    for(int i = 2; i < N + 1; i++){
        for(int j = 0; j < K + 1; j++){
            if(K - j >= i - 1){
                dp[i][j] = -1;
                continue;
            }
            dp[i][j] = 999999999;
            if(dp[i - 1][j] != -1){
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dist(checks[i - 1], checks[i]));
            }
            for(int a = 2; a <= K + 1; a++){
                if(i - a < 1 || j + a - 1 > K){
                    break;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - a][j - 1 + a] + dist(checks[i - a], checks[i]));
            }
        }
    }
    
    int ans = 999999999;
    for(int i = 0; i < K + 1; i++){
        ans = Math.min(dp[N][i], ans);
    }
    PrintWriter out = new PrintWriter(new File("marathon.out"));
    out.println(ans);
    out.close();
  }
  
  public static int dist(pair a, pair b){
      return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }
}

class pair{
    int x;
    int y;
    public pair(int a, int b){
        x = a;
        y = b;
    }
}