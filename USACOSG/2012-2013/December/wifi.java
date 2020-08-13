import java.util.*;
import java.io.*;
public class wifi{
    public static void main(String[] args) throws Exception{
        /*
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        */
        BufferedReader f = new BufferedReader(new FileReader("wifi.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int A = Integer.parseInt(s.nextToken());
        int B = Integer.parseInt(s.nextToken());
        int[] array = new int[N];
        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(array);
        double[] dp = new double[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = A;
        for(int i = 1; i < N; i++){
            dp[i] = Math.min(dp[i], A + B * (0.0 + array[i] - array[0]) / 2);
            for(int j = 0; j < i; j++){
                dp[i] = Math.min(dp[i], dp[j] + A + B * (0.0 + array[i] - array[j + 1]) / 2);
            }
        }
        PrintWriter out = new PrintWriter(new File("wifi.out"));
        if(Math.abs(dp[N - 1] - (int) (dp[N - 1] + 0.5)) < 1e-9){
            out.println((int) (dp[N - 1] + 0.5));
        }else{
            out.println(dp[N - 1]);
        }
        out.close();
    }
}