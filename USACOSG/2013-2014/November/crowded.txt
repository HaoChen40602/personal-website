/*
Hao Chen
6/26/2020
*/
import java.util.*;
import java.io.*;
import java.awt.Point;
import static java.lang.Math.*;

public class crowded{
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("crowded.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int D = Integer.parseInt(s.nextToken());
        cow[] cows = new cow[N];
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(s.nextToken());
            int h = Integer.parseInt(s.nextToken());
            cows[i] = new cow(p, h);
        }
        Arrays.sort(cows);
        boolean[] a = solve(cows, D);
        
        for(int i = 0; i < cows.length / 2; i++){
            cow temp = cows[i];
            cows[i] = cows[cows.length - i - 1];
            cows[cows.length - i - 1] = temp;
        }
        for(int i = 0; i < N; i++){
            cows[i].pos *= -1;
        }
        boolean[] b = solve(cows, D);
        int ans = 0;
        for(int i = 0; i < N; i++){
            if(a[i] && b[N - 1 - i]){
                ans++;
            }
        }
        out.println(ans);
        out.flush();
    }
    
    public static boolean[] solve(cow[] cows, int D){
        int left = 0;
        LinkedList<cow> max = new LinkedList<cow>();
        boolean[] arr = new boolean[cows.length];
        for(int right = 0; right < cows.length; right++){
            //process max
            while(max.isEmpty() == false && max.getLast().height < cows[right].height){
                max.removeLast();
            }
            max.add(new cow(cows[right].pos, cows[right].height));
            
            // increase left bound
            while(right >= left && cows[right].pos - cows[left].pos > D){
                left++;
                while(max.isEmpty() == false && max.peekFirst().pos < cows[left].pos){
                    max.pollFirst();
                }
            }
            if(max.peekFirst().height >= 2 * cows[right].height){
                arr[right] = true;
            }
        }
        return arr;
    }
}

class cow implements Comparable<cow>{
    public int pos;
    public int height;
    public cow(int p, int h){
        pos = p;
        height = h;
    }
    public int compareTo(cow other){
        return this.pos - other.pos;
    }
}