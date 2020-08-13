import java.util.*;
import java.io.*;
import java.awt.Point;
import static java.lang.Math.*;

public class pogocow{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("pogocow.in"));
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
        int N = Integer.parseInt(f.readLine());
        point[] points = new point[N];
        
        for(int i = 0; i < N; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(s.nextToken());
            int v = Integer.parseInt(s.nextToken());
            points[i] = new point(p, v);
        }
        
        Arrays.sort(points);
        int[][] dp = new int[N][N];
        int result = 0;
        for(int ii = 0; ii < 2; ii++){
            for(int i = N - 1; i >= 0; i--){
                int k = N;
                int val = 0;
                for(int j = 0; j <= i; j++) {
                    while(k - 1 > i && points[k - 1].pos - points[i].pos >= points[i].pos - points[j].pos){
                        k -= 1;
                        val = Math.max(val, points[k].val + dp[k][i]);
                    }
                    dp[i][j] = val;
                }
                result = Math.max(result, points[i].val + val);
            }
            
            for(int i = 0; i < N; i++) {
                points[i].pos *= -1;
            }
            for(int i = 0; i < points.length / 2; i++){
                point temp = points[i];
                points[i] = points[points.length - i - 1];
                points[points.length - i - 1] = temp;
            }
        }
        out.println(result);
        out.flush();
    }
}

class point implements Comparable<point>{
    public int pos;
    public int val;
    public point(int p, int v){
        pos = p;
        val = v;
    }
    public int compareTo(point other){
        return this.pos - other.pos;
    }
}