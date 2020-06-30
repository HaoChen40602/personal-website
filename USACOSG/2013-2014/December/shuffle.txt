import java.util.*;
import java.io.*;

public class shuffle{
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int M = Integer.parseInt(s.nextToken());
        int Q = Integer.parseInt(s.nextToken());
        int[] change = new int[N];
        for(int i = 0; i < M; i++){
            change[i] = Integer.parseInt(f.readLine());
        }
        for(int i = M; i < N; i++){
            change[i] = i + 1;
        }
        for(int i = 0; i < N; i++){
            change[i] -= 2;
            if(change[i] == -1){
                change[i] = N - 1;
            }
        }
        int[][] changes = new int[(int) (Math.log(N) / Math.log(2)) + 1][N];
        changes[0] = Arrays.copyOf(change, change.length);
        for(int i = 1; i < changes.length; i++){
            for(int j = 0; j < N; j++){
                changes[i][j] = changes[i - 1][changes[i - 1][j]];
            }
        }
        String binary = Integer.toBinaryString(N - M + 1);
        int[] ans = new int[N];
        for(int i = 0; i < ans.length; i++){
            ans[i] = i + 1;
        }
        for(int i = 0; i < binary.length(); i++){
            char c = binary.charAt(binary.length() - 1 - i);
            int[] next = new int[N];
            if(c == '1'){
                for(int j = 0; j < ans.length; j++){
                    next[changes[i][j]] = ans[j];
                }
                ans = Arrays.copyOf(next, N);
            }
        }
        ArrayList<Integer> next = new ArrayList<Integer>(N);
        for(int i = M - 1; i < N; i++){
            next.add(ans[i]);
        }
        for(int i = 0; i < M - 1; i++){
            next.add(ans[i]);
        }
        Collections.reverse(next);
        PrintWriter out = new PrintWriter(new File("shuffle.out"));
        for(int i = 0; i < Q; i++){
            int temp = Integer.parseInt(f.readLine());
            out.println(next.get(temp - 1));
        }
        out.close();
    }
}
