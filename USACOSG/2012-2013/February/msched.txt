import java.util.*;
import java.io.*;
import java.awt.Point;
import static java.lang.Math.*;

public class msched {
    static int[] C;
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("msched.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(s.nextToken());
        int m = Integer.parseInt(s.nextToken());
        C = new int[n];
        
        for(int i = 0; i < n; i++) {
            C[i] = Integer.parseInt(f.readLine());
        }
        int[] D = new int[n];
        ArrayList<ArrayList<Integer>> E = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++){
            E.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < m; i++) {
            s = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(s.nextToken()) - 1;
            int y = Integer.parseInt(s.nextToken()) - 1;
            E.get(x).add(y);
            D[y]++;
        }
        
        Comparator comp = new Comparator<int[]>() {
                public int compare(int[] A, int[] B) {
                    return A[1] - B[1];
                }
            };
        
        PriorityQueue<int[]> Q = new PriorityQueue<int[]>(10, comp);
        
        for(int i = 0; i < n; i++){
            if(D[i] == 0) {
                Q.add(new int[]{i, C[i]});
            }
        }
        int ans = 0;
        
        
        while(!Q.isEmpty()) {
            int[] e = Q.poll();
            int x = e[0];
            int t = e[1];
            ans = Math.max(ans, t);
            for(Integer y : E.get(x)){
                D[y]--;
                if(D[y] == 0) {
                    Q.add(new int[]{y, C[y] + t});
                }
            }
        }

        out.println(ans);
        out.flush();
    }
}
