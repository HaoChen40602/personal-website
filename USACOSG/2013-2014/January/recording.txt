import java.util.*;
import java.io.*;
public class recording{
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("recording.in"));
        int N = Integer.parseInt(f.readLine());
        item[] items = new item[N];
        for(int i = 0; i < N; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            int ss = Integer.parseInt(s.nextToken());
            int ee = Integer.parseInt(s.nextToken());
            items[i] = new item(ss, ee);
        }
        
        Arrays.sort(items);
        int first = 0;
        int second = 0;
        int ans = 0;
        
        for(int i = 0; i < N; i++){
            if(items[i].start >= first && items[i].start >= second){
                if(first >= second){
                    first = items[i].end;
                }else{
                    second = items[i].end;
                }
                ans++;
            }else if(items[i].start >= first){
                first = items[i].end;
                ans++;
            }else if(items[i].start >= second){
                second = items[i].end;
                ans++;
            }
        }
        PrintWriter out = new PrintWriter(new File("recording.out"));
        out.println(ans);
        out.close();
    }
} 

class item implements Comparable<item>{
    int start;
    int end;
    public item(int s, int e){
        start = s;
        end = e;
    }
    public int compareTo(item other){
        return this.end - other.end;
    }
}