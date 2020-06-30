import java.util.*;
import java.io.*;
public class lazy{
    static int N;
    static int K;
    static int[][] matrix;
    static int[][] prefix;
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("lazy.in"));
        PrintWriter out = new PrintWriter(new File("lazy.out"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        N = Integer.parseInt(s.nextToken());
        K = Integer.parseInt(s.nextToken());
        matrix = new int[N][N];
        prefix = new int[N][N];
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            for(int j = 0; j < N; j++){
                matrix[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            prefix[i][0] = matrix[i][0];
            for(int j = 1; j < N; j++){
                prefix[i][j] = prefix[i][j - 1] + matrix[i][j];
            }
        }
        int ans = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int current = 0;
                for(int a = Math.max(0, i - K); a <= Math.min(i + K, N - 1); a++){
                    int steps = K - Math.abs(i - a);
                    if(j - steps - 1 >= 0){
                        current += prefix[a][Math.min(j + steps, N - 1)] - prefix[a][j - steps - 1];
                    }else{
                         current += prefix[a][Math.min(j + steps, N - 1)] - 0;
                    }
                }
                ans = Math.max(current, ans);
            }
        }
        out.println(ans);
        out.close();
    }
    
}
