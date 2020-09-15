import java.util.*;
import java.io.*;
public class cbarn{
    public static void main(String[] args) throws Exception{
        
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
        
        int N = Integer.parseInt(f.readLine());
        long[] array = new long[N];
        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(f.readLine());
        }
        long[] waiting = new long[N];
        waiting[0] = array[0] - 1;
        long min = waiting[0];
        int minIndex = 0;
        for(int i = 1; i < N; i++){
            waiting[i] = waiting[i - 1] + array[i] - 1;
            if(waiting[i] <= min){
                min = waiting[i];
                minIndex = i;
            }
        }
        
        LinkedList<Integer> q = new LinkedList<Integer>();
        int count = (minIndex + 1) % N;
        long ans = 0;
        
        do{
            if(array[count] == 0){
                int from = q.poll();
                if(count >= from){
                    ans += (count - from) * (count - from);
                }else{
                    ans += (count + N - from) * (count + N - from);
                }
            }else{
                for(int i = 0; i < array[count]; i++){
                    q.add(count);
                }
                int from = q.poll();
                if(count >= from){
                    ans += (count - from) * (count - from);
                }else{
                    ans += (count + N - from) * (count + N - from);
                }
                
            }
            count = (count + 1) % N;
        }while(count != minIndex + 1);
        System.out.println(ans);
    }
}