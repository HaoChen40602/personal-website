import java.util.*;
import java.io.*;
public class cardgame{
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("cardgame.in"));
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("cardgame.out"));
        int N = Integer.parseInt(f.readLine());
        boolean[] num = new boolean[N * 2 + 1];
        int[] E1 = new int[N / 2];
        int[] E2 = new int[N / 2];
        TreeSet<Integer> B1 = new TreeSet<Integer>();
        TreeSet<Integer> B2 = new TreeSet<Integer>();
        for(int i = 0; i < N / 2; i++){
            E1[i] = Integer.parseInt(f.readLine());
            num[E1[i]] = true;
        }
        for(int i = 0; i < N / 2; i++){
            E2[i] = Integer.parseInt(f.readLine());
            num[E2[i]] = true;
        }
        
        for(int i = 1; i < N * 2 + 1; i++){
            if(!num[i]){
                if(B2.size() < N / 2){
                    B2.add(i);
                }else{
                    B1.add(i);
                }
            }
        }
        int ans = 0;
        Arrays.sort(E1);
        Arrays.sort(E2);
        for(int i = 0; i < N / 2; i++){
            Integer current = B1.higher(E1[i]);
            if(current != null){
                ans++;
                B1.remove(current);
            }else{
                break;
            }
        }
        for(int i = N / 2 - 1; i >= 0; i--){
            Integer current = B2.lower(E2[i]);
            if(current != null){
                ans++;
                B2.remove(current);
            }else{
                break;
            }
        }
        out.println(ans);
        out.close();
    }
}
