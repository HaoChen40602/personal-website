import java.util.*;
import java.io.*;
public class feast{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("feast.in"));
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("feast.out"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int T = Integer.parseInt(s.nextToken());
        int A = Integer.parseInt(s.nextToken());
        int B = Integer.parseInt(s.nextToken());
        boolean[][] dp = new boolean[2][T + 1];
        dp[0][0] = true;
        dp[1][0] = true;
        for(int a = 0; a < dp.length; a++) {
            for(int i = 0; i < dp[a].length; i++) {
                if(!dp[a][i]) {
                    continue;
                }
                if(i + A <= T) {
                    dp[a][i + A] = true;
                }
                if(i + B <= T) {
                    dp[a][i + B] = true;
                }
                if(a + 1 < dp.length){
                    dp[a + 1][i / 2] = true;
                }
            }
        }
        
        for(int i = T; i >= 0; i--){
            if(dp[0][i] || dp[1][i]){
                System.out.println(i);
                break;
            }
        }
        out.close();
    }
}
