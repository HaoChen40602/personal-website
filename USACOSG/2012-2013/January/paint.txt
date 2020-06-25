import java.util.*;
import java.io.*;
public class paint{
    public static void main(String[] args) throws Exception{
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("paint.in"));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int K = Integer.parseInt(s.nextToken());
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int location = 0;
        for(int i = 0; i < N; i++){
            s = new StringTokenizer(f.readLine());
            int dist = Integer.parseInt(s.nextToken());
            char a = s.nextToken().charAt(0);
            int next = 0;
            if(a == 'L'){
                next = location - dist;
                if(map.containsKey(next)){
                    map.put(next, map.get(next) + 1);
                    if(map.get(next) == 0){
                        map.remove(next);
                    }
                }else{
                    map.put(next, 1);
                }
                
                if(map.containsKey(location)){
                    map.put(location, map.get(location) - 1);
                    if(map.get(location) == 0){
                        map.remove(location);
                    }
                }else{
                    map.put(location, -1);
                }
            }else{
                next = location + dist;
                if(map.containsKey(location)){
                    map.put(location, map.get(location) + 1);
                    if(map.get(location) == 0){
                        map.remove(location);
                    }
                }else{
                    map.put(location, 1);
                }
                
                if(map.containsKey(next)){
                    map.put(next, map.get(next) - 1);
                    if(map.get(next) == 0){
                        map.remove(next);
                    }
                }else{
                    map.put(next, -1);
                }
            }
            location = next;
        }
        int layer = 0;
        int ans = 0;
        int previous = Integer.MIN_VALUE;
        for(int i : map.navigableKeySet()){
            if(layer >= K){
                ans += i - previous;
            }
            layer += map.get(i);
            previous = i;
        }
        PrintWriter out = new PrintWriter(new File("paint.out"));
        out.println(ans);
        out.close();
    }
}