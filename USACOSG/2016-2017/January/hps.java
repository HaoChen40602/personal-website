import java.io.*;
import java.util.*;
public class hps{
    public static void main(String[] args) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int K = Integer.parseInt(s.nextToken());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            char current = f.readLine().charAt(0);
            if(current == 'H'){
                arr[i] = 0;
            }else if(current == 'P'){
                arr[i] = 1;
            }else if(current == 'S'){
                arr[i] = 2;
            }
        }
        int[][][] dp = new int[N][K + 1][3];
        if(arr[0] == 0){
            dp[0][0][1] = 1;
        }else if(arr[0] == 1){
            dp[0][0][2] = 1;
        }else{
            dp[0][0][0] = 1;
        }
        int ans = 0;
        for(int i = 1; i < N; i++){
            for(int j = 0; j < K + 1; j++){
                if(j > i){
                    continue;
                }
                for(int a = 0; a < 3; a++){
                    if(a == (arr[i] + 1) % 3){
                        dp[i][j][a] = Math.max(dp[i - 1][j][a] + 1, dp[i][j][a]);
                        if(j != 0){
                            dp[i][j][a] = Math.max(dp[i - 1][j - 1][0] + 1, dp[i][j][a]);
                            dp[i][j][a] = Math.max(dp[i - 1][j - 1][1] + 1, dp[i][j][a]);
                            dp[i][j][a] = Math.max(dp[i - 1][j - 1][2] + 1, dp[i][j][a]);
                        }
                    }else{
                        dp[i][j][a] = Math.max(dp[i - 1][j][a], dp[i][j][a]);
                    }
                    ans = Math.max(dp[i][j][a], ans);
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}