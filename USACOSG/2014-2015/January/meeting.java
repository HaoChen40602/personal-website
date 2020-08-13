import java.util.*;
import java.io.*;

public class meeting{
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("meeting.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        PrintWriter out = new PrintWriter(new File("meeting.out"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int M = Integer.parseInt(s.nextToken());
        LinkedList<edge>[] g = new LinkedList[N];
        for(int i = 0; i < N; i++){
            g[i] = new LinkedList<edge>();
        }
        for(int i = 0; i < M; i++){
            s = new StringTokenizer(f.readLine());
            int A = Integer.parseInt(s.nextToken()) - 1;
            int B = Integer.parseInt(s.nextToken()) - 1;
            int C = Integer.parseInt(s.nextToken());
            int D = Integer.parseInt(s.nextToken());
            g[A].add(new edge(B, C, D));
        }
        TreeSet<Integer>[] Bessie = new TreeSet[N];
        TreeSet<Integer>[] Elsie = new TreeSet[N];
        for(int i = 0; i < N; i++){
            Bessie[i] = new TreeSet<Integer>();
            Elsie[i] = new TreeSet<Integer>();
        }
        Bessie[0].add(0);
        Elsie[0].add(0);
        for(int i = 0; i < N - 1; i++){
            for(edge e : g[i]){
                for(int t : Bessie[i]){
                    Bessie[e.to].add(t + e.Bessie);
                }
                for(int t : Elsie[i]){
                    Elsie[e.to].add(t + e.Elsie);
                }
            }
        }
        int ans = -1;
        for(int i : Bessie[N - 1]){
            if(Elsie[N - 1].contains(i)){
                ans = i;
                break;
            }
        }
        if(ans == -1){
            out.println("IMPOSSIBLE");
        }else{
            out.println(ans);
        }
        out.close();
    }
}

class edge{
    int to;
    int Bessie;
    int Elsie;
    public edge(int t, int B, int E){
        to = t;
        Bessie = B;
        Elsie = E;
    }
}
