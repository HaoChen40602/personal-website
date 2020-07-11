import java.util.*;
import java.io.*;
public class stampede{
    public static void main(String[] args) throws Exception {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("stampede.in"));
        PrintWriter out = new PrintWriter(new File("stampede.out"));
        int N = Integer.parseInt(f.readLine());
        event[] arr = new event[2 * N];
        for(int i = 0; i < N; i++){
            StringTokenizer s = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(s.nextToken());
            int y = Integer.parseInt(s.nextToken());
            int r = Integer.parseInt(s.nextToken());
            arr[2 * i] = new event(x * -r - r, y, true);
            arr[2 * i + 1] = new event(x * -r, y, false);
        }
        Arrays.sort(arr);
        HashSet<Integer> canC = new HashSet<Integer>();
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 0; i < arr.length; i++){
            int j;
            for (j = i; j < arr.length && arr[i].time == arr[j].time; j++) {
                int y = arr[j].y;
                if(arr[j].isStart){
                    set.add(y);
                }else{
                    set.remove(y);
                }
            }
            if(set.isEmpty() == false){
                canC.add(set.first());
            }
            i = j - 1;
        }
        out.println(canC.size());
        out.close();
    }
}

class event implements Comparable<event>{
    int y;
    int time;
    boolean isStart;
    public event(int time, int y, boolean isStart){
        this.y = y;
        this.time = time;
        this.isStart = isStart;
    }
    public int compareTo(event other){
        return this.time - other.time;
    }
}
