import java.util.*;
import java.io.*;

public class cbarn2{

    public static void main(String[] args) throws Exception{

        Scanner stdin = new Scanner(new File("cbarn2.in"));
        PrintWriter out = new PrintWriter(new FileWriter("cbarn2.out"));
        int n = stdin.nextInt();
        int open = stdin.nextInt();

        int[] vals = new int[n];
        for (int i = 0; i < n; i++){
            vals[i] = stdin.nextInt();
        }
        
        
        long res = Long.MAX_VALUE;
        for (int start = 0; start < n; start++){
            int[] arr = new int[n];
            for(int i = start; i < start + n; i++){
                arr[i - start] = vals[i % n];
            }
            long[][] dp = new long[open][n];
            
            for (int i = 1; i < n; i++){
                dp[0][i] = dp[0][i - 1] + i * arr[i];
            }
            for (int i = 1; i < open; i++){
                for (int j = i + 1; j < n; j++){
                    dp[i][j] = dp[i - 1][j];
                    int sum = 0;
                    int people = arr[j];
                    int k = j - 1;
                    while (k > 0 && sum < dp[i][j]){
                        sum += people;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + sum);
                        people += arr[k];
                        k--;
                    }
                }
            }
            
            res = Math.min(res, dp[open - 1][n - 1]);
        }
        out.println(res);
        out.close();
    }
}