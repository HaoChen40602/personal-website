import java.util.*;
import java.io.*;
public class cowrun{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("cowrun.in"));
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        int N = Integer.parseInt(f.readLine());
        int[] cows = new int[N + 4];
        for(int i = 1; i < N + 1; i++){
            cows[i] = Integer.parseInt(f.readLine());
        }
        cows[++N] = 0;
        Arrays.sort(cows, 1, cows.length);
        
        int[][][] best = new int[N + 2][N + 2][2];
        for(int i = 0; i < N + 2; i++){
            for(int j = 0; j < N + 2; j++){
                Arrays.fill(best[i][j], 99999999);
            }
        }
        
        for(int i = 1; i <= N; i++){
            if(cows[i] == 0){
                best[i][1][0] = 0;
            }
        }
        
        for (int len = 1; len < N; len++){
            int ccount = N - len;
            for (int i = 1; i + len <= N + 1; i++){
                best[i - 1][len + 1][0] = Math.min(best[i - 1][len + 1][0], best[i][len][0] + ccount * (cows[i] - cows[i - 1]));
                best[i - 1][len + 1][0] = Math.min(best[i - 1][len + 1][0], best[i][len][1] + ccount * (cows[i + len - 1] - cows[i - 1]));
                best[i][len + 1][1] = Math.min(best[i][len + 1][1], best[i][len][0] + ccount * (cows[i + len] - cows [i]));
                best[i][len + 1][1] = Math.min(best[i][len + 1][1], best[i][len][1] + ccount * (cows[i + len] - cows [i + len - 1]));
            }
        }
        
        PrintWriter out = new PrintWriter(new File("cowrun.out"));
        out.println(Math.min(best[1][N][0], best[1][N][1]));
        out.close();
    }
}