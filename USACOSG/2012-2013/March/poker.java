import java.util.*;
import java.io.*;
public class poker{
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("poker.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        int N = Integer.parseInt(f.readLine());
        int[] arr = new int[N];
        int[] num = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(f.readLine());
        }
        num[0] = arr[0];
        for(int i = 1; i < N; i++){
            if(arr[i] <= arr[i - 1]){
                num[i] = 0;
            }else{
                num[i] = arr[i] - arr[i - 1];
            }
        }
        long ans = 0;
        for(int i = 0; i < N; i++){
            ans += num[i];
        }
        PrintWriter out = new PrintWriter(new File("poker.out"));
        out.println(ans);
        out.close();
    }
} 