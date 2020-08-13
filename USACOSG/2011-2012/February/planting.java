import java.io.*;
import java.util.*;
import java.lang.Math;
public class planting{
  public static void main (String [] args) throws IOException {
    
    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer s = new StringTokenizer(f.readLine());
    int N = Integer.parseInt(s.nextToken());
    edge[] critical = new edge[2 * N];
    for(int i = 0; i < N; i++){
        s = new StringTokenizer(f.readLine());
        int x1 = Integer.parseInt(s.nextToken());
        int y1 = Integer.parseInt(s.nextToken());
        int x2 = Integer.parseInt(s.nextToken());
        int y2 = Integer.parseInt(s.nextToken());
        critical[2 * i] = new edge(x1, y2, y1, true);
        critical[2 * i + 1] = new edge(x2, y2, y1, false);
    }
    Arrays.sort(critical);
    long ans = 0;
    TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
    for(int i = 0; i < 2 * N - 1; i++){
        if(critical[i].isS){
            if(tmap.containsKey(critical[i].start)){
                tmap.put(critical[i].start, tmap.get(critical[i].start) + 1);
                if(tmap.get(critical[i].start) == 0){
                    tmap.remove(critical[i].start);
                }
            }else{
                tmap.put(critical[i].start, 1);
            }
            
            if(tmap.containsKey(critical[i].end)){
                tmap.put(critical[i].end, tmap.get(critical[i].end) - 1);
                if(tmap.get(critical[i].end) == 0){
                    tmap.remove(critical[i].end);
                }
            }else{
                tmap.put(critical[i].end, -1);
            }
        }else{
            if(tmap.containsKey(critical[i].start)){
                tmap.put(critical[i].start, tmap.get(critical[i].start) - 1);
                if(tmap.get(critical[i].start) == 0){
                    tmap.remove(critical[i].start);
                }
            }else{
                tmap.put(critical[i].start, -1);
            }
            
            if(tmap.containsKey(critical[i].end)){
                tmap.put(critical[i].end, tmap.get(critical[i].end) + 1);
                if(tmap.get(critical[i].end) == 0){
                    tmap.remove(critical[i].end);
                }
            }else{
                tmap.put(critical[i].end, 1);
            }
        }
        long area = 0;
        long temp = 0;
        java.lang.Object[] arr = tmap.navigableKeySet().toArray();
        for(int a = 0; a < arr.length - 1; a++){
            temp += tmap.get(arr[a]);
            if(temp != 0){
                area += ((int) arr[a + 1]) - ((int) arr[a]);
            }
        }
        ans += area * (critical[i + 1].x - critical[i].x);
    }
    System.out.println(ans);
  }
}
class edge implements Comparable<edge>{
    int x;
    int start;
    int end;
    boolean isS;
    public edge(int x, int start, int end, boolean i){
        this.x = x;
        this.start = start;
        this.end = end;
        isS = i;
    }
    
    public int compareTo(edge other){
        return this.x - other.x;
    }
}