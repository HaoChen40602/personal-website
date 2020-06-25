import java.util.*;
import java.io.*;

public class clumsy {
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("clumsy.in"));
        String str = f.readLine();
        int[] arr = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                arr[i] = 1;
            }else{
                arr[i] = -1;
            }
        }
        int sum = 0;
        int ans = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(sum < 0){
                ans++;
                sum += 2;
            }
        }
        ans += sum / 2;
        PrintWriter out = new PrintWriter(new File("clumsy.out"));
        out.println(ans);
        out.close();
    }
}
