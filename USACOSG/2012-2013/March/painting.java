import java.util.*;
import java.io.*;
public class painting{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("painting.in"));
        BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        int N = Integer.parseInt(f.readLine());
        edge[] edges = new edge[2 * N];
        boolean[] isOut = new boolean[N];
        for(int i = 0; i < N; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(s.nextToken());
            int y1 = Integer.parseInt(s.nextToken());
            int x2 = Integer.parseInt(s.nextToken());
            int y2 = Integer.parseInt(s.nextToken());
            edges[2 * i] = new edge(x1, y1, y2, i, true);
            edges[2 * i + 1] = new edge(x2, y1, y2, i, false);
        }
        
        Arrays.sort(edges);
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int i = 0; i < 2 * N; i++){
            if(edges[i].isStart){
                boolean shouldAdd = true;
                for(int[] k : list){
                    if(edges[i].yLow >= k[0] && edges[i].yHigh <= k[1]){
                        shouldAdd = false;
                        continue;
                    }
                }
                if(shouldAdd){
                    isOut[edges[i].id] = true;
                    list.add(new int[]{edges[i].yLow, edges[i].yHigh, edges[i].id});
                }
            }else{
                if(isOut[edges[i].id]){
                    for(int j = list.size() - 1; j >= 0; j--){
                        if(list.get(j)[2] == edges[i].id){
                            list.remove(j);
                            break;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for(boolean a : isOut){
            if(a){
                ans++;
            }
        }
        PrintWriter out = new PrintWriter(new File("painting.out"));
        out.println(ans);
        out.close();
    }
} 

class edge implements Comparable<edge>{
    int x;
    int yLow;
    int yHigh;
    int id;
    boolean isStart;
    public edge(int x, int yl, int yh, int id, boolean is){
        this.x = x;
        yLow = yl;
        yHigh = yh;
        this.id = id;
        isStart = is;
    }
    
    public int compareTo(edge other){
        return this.x - other.x;
    }
}