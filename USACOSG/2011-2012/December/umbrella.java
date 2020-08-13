import java.io.*;
import java.util.*;
import java.lang.Math;
public class umbrella{
  public static void main (String [] args) throws IOException {
    /*
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    */
    BufferedReader f = new BufferedReader(new FileReader("umbrella.in"));
    StringTokenizer s = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(s.nextToken());
    int M = Integer.parseInt(s.nextToken());
    int[] array = new int[N];
    int[] cost = new int[M + 1];
    for(int i = 0; i < N; i++){
        array[i] = Integer.parseInt(f.readLine());
    }
    for(int i = 0; i < M; i++){
        cost[i + 1] = Integer.parseInt(f.readLine());
    }
    int min = cost[M];
    for(int i = M - 1; i >= 1; i--){
        min = Math.min(min, cost[i]);
        cost[i] = min;
    }
    Arrays.sort(array);
    int[] dp = new int[N];
    for(int i = 0; i < N; i++){
        dp[i] = Integer.MAX_VALUE;
        for(int j = 1; j <= i; j++){
            dp[i] = Math.min(dp[i], dp[j - 1] + cost[array[i] - array[j] + 1]);
        }
        // here I account for the case that the umbrella covers cow 0 to cow i inclusive;
        dp[i] = Math.min(dp[i], cost[array[i] - array[0] + 1]);
    }
    System.out.println(dp[N - 1]);
  }
}

