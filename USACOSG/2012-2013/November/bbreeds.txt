import java.util.*;
import java.io.*;
public class bbreeds{
    static char[] S;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("bbreeds.in"));
        String str = f.readLine();
        S = new char[str.length() + 1];
        int[] prefix = new int[str.length() + 1];
        for(int i = 1; i < S.length; i++){
            S[i] = str.charAt(i - 1);
            if(S[i] == '('){
                prefix[i] = prefix[i - 1] + 1;
            }else{
                prefix[i] = prefix[i - 1] - 1;
            }
        }
        dp = new int[2][S.length / 2 + 1];
        dp[0][0] = 1;
        for(int i = 1; i < S.length; i++){
            for(int a = 0; a < S.length / 2 + 1; a++){
                int b = prefix[i] - a;
                if(b < 0 || b > S.length / 2 + 1){
                    continue;
                }
                dp[i % 2][a] = 0;
                if(S[i] == '('){
                    if(a != 0){
                        dp[i % 2][a] += dp[(i - 1) % 2][a - 1];
                        dp[i % 2][a] %= 2012;
                    }
                    if(b != 0){
                        dp[i % 2][a] += dp[(i - 1) % 2][a];
                        dp[i % 2][a] %= 2012;
                    }
                }else{
                    if(a != S.length / 2){
                        dp[i % 2][a] += dp[(i - 1) % 2][a + 1];
                        dp[i % 2][a] %= 2012;
                    }
                    if(b != S.length / 2){
                        dp[i % 2][a] += dp[(i - 1) % 2][a];
                        dp[i % 2][a] %= 2012;
                    }
                }
            }
        }
        PrintWriter out = new PrintWriter(new File("bbreeds.out"));
        System.out.println(dp[(S.length - 1) % 2][0] % 2012);
        out.close();
    }
} 
