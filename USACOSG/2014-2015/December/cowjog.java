//USACO 2014 SILVER PROBLEM 3
//2020/6/22
//Hao Chen
import java.io.*;
import java.util.*;
import java.lang.Math;
public class cowjog{
  public static void main (String [] args) throws IOException {
    //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
    BufferedReader f = new BufferedReader(new FileReader("cowjog.in"));
    PrintWriter out = new PrintWriter(new File("cowjog.out"));
    StringTokenizer s = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(s.nextToken());
    long T = Integer.parseInt(s.nextToken());
    long[] end = new long[N];
    for(int i = 0; i < N; i++){
        s = new StringTokenizer(f.readLine());
        long st = Integer.parseInt(s.nextToken());
        long sp = Integer.parseInt(s.nextToken());
        end[i] = st + sp * T;
    }
    long min = end[N - 1];
    long ans = 1;
    for(int i = N - 1; i >= 0; i--){
        if(end[i] < min){
            ans++;
        }
        min = Math.min(min, end[i]);
    }
    
    out.println(ans);
    out.close();
  }
}

class cow{
    long start;
    long speed;
    long end;
    public cow(long st,long sp, long t){
        start = st;
        speed = sp;
        end = st + sp * t;
    }
}