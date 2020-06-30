import java.util.*;
import java.io.*;

public class mooomoo {

    public static void main(String[] args) throws Exception {
        Scanner f = new Scanner(new File("mooomoo.in"));
        int n = f.nextInt();
        int numTypes = f.nextInt();
        int[] vals = new int[numTypes];
        for (int i = 0; i < numTypes; i++){
            vals[i] = f.nextInt();
        }
        int[] totalSound = new int[n];
        for (int i = 0; i < n; i++){
            totalSound[i] = f.nextInt();
        }
        int cur = 0;
        int max = 0;
        
        for (int i = 0; i < n; i++){
            int newcur = totalSound[i];
            totalSound[i] -= cur;
            cur = newcur;
            if (cur > 0){
                cur--;
            }
            
            max = Math.max(max, totalSound[i]);
        }
        
        int[] dp = new int[max + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for (int i = 0; i < numTypes; i++){
            for (int j = vals[i]; j < dp.length; j++){
                if (dp[j - vals[i]] != -1){
                    if (dp[j] == -1 || dp[j - vals[i]] + 1 < dp[j]){
                        dp[j] = dp[j - vals[i]] + 1;
                    }
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < n; i++){
            if (dp[totalSound[i]] == -1) {
                res = -1;
                break;
            }
            res += dp[totalSound[i]];
        }
        
        PrintWriter out = new PrintWriter(new File("mooomoo.out"));
        out.println(res);
        out.close();
    }
}