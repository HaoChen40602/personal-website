import java.io.*;
import java.util.*;
public class barnjump {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("barnjump.in"));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barnjump.out")));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int[][] grid = new int[r][c];
    for(int i = 0; i < r; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < c; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    final int MOD = 1000000007;
    int[][] dp = new int[r][c];
    dp[0][0] = 1;
    for(int i = 0; i < r; i++) {
      for(int j = 0; j < c; j++) {
        for(int k = i+1; k < r; k++) {
          for(int l = j+1; l < c; l++) {
            if(grid[i][j] != grid[k][l]) {
              dp[k][l] += dp[i][j];
              dp[k][l] %= MOD;
            }
          }
        }
      }
    }
    pw.println(dp[r-1][c-1]);
    pw.close();
  }
  
}