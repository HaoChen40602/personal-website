
import java.util.*;
import java.io.*;

public class angry {
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(f.readLine().trim());
        long[] vals = new long[n];
        for (int i = 0; i < n; i++){
            vals[i] = Long.parseLong(f.readLine().trim());
        }
        Arrays.sort(vals);
        long[] rev = new long[n];
        for (int i=0; i<n; i++){
            rev[i] = vals[n-1-i];
        }
        double low = vals[0], high = vals[n - 1];
        double res = vals[n - 1] - vals[0];
        while (high-low > .001){
            double mid = (low + high) / 2;
            int lowIndex = getLowIndex(vals, mid);  
            double left = binSearch(vals, mid, lowIndex);
            double right = binSearch(rev, mid, n - 2 - lowIndex);
            res = Math.min(Math.max(left,right), res);
            if (left < right){
                low = mid;
            }else{
                high = mid;
            }
        }

        long newRes = (long)(100 * res);
        long whole = (long)res;
        int tens = (int)(newRes % 100 - newRes % 10) / 10;
        
        if (newRes % 10 >= 5){
            tens++;
        }
        if (tens == 10) {
            whole = whole + 1;
            tens = 0;
        }

        System.out.println((newRes / 100) + "." + tens);
    }

    public static int getLowIndex(long[] vals, double x) {
        int i = 0;  
        while (i < vals.length && vals[i] < x){
            i++;
        }
        return i-1;
    }

    public static double binSearch(long[] arr, double value, int index) {
        double low = .5, high = Math.abs(arr[0] - value);
        while(high - low > .001) {
            double mid = (low + high) / 2;
            double saveMid = mid, cur = value;
            int i = index;

            while(i > 0) {
                int j = i;
                while(j >= 0 && Math.abs(arr[j] - cur) <= mid + 1e-9){
                    j--;
                }
                if(j == i){
                    break;
                }
                i = j + 1;
                mid = mid - 1;
                cur = arr[i];
                i--;
            }

            if(i <= 0){
                high = saveMid;
            }else{
                low = saveMid;
            }
        }
        return high;
    }

}