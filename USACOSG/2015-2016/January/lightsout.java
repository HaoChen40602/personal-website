import java.util.*;
import java.io.*;
public class lightsout {
    public static void main(String[] args) throws Exception {

        Scanner stdin = new Scanner(new File("lightsout.in"));
        //Scanner stdin = new Scanner(new File("README.txt"));
        int n = stdin.nextInt();
        long[][] pts = new long[n][2];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 2; j++){
                pts[i][j] = stdin.nextLong();
            }
        }
        
        long[] len = new long[n];
        for (int i = 0; i < n; i++){
            len[i] = Math.abs(pts[i][0] - pts[(i + 1) % n][0]) + Math.abs(pts[i][1] - pts[(i + 1) % n][1]);
        }
        
        long[] cumfreqClock = new long[n];
        cumfreqClock[n - 1] = len[n - 1];
        for (int i = n - 2; i > 0; i--){
            cumfreqClock[i] = cumfreqClock[i + 1] + len[i];
        }
        
        long[] cumfreqCounter = new long[n];
        cumfreqCounter[1] = len[0];
        for (int i = 2; i < n; i++){
            cumfreqCounter[i] = cumfreqCounter[i - 1] + len[i - 1];
        }
        boolean[] angle = new boolean[n];
        for (int i = 0; i < n; i++) {
            long v1x = pts[i][0] - pts[(i - 1 + n) % n][0];
            long v1y = pts[i][1] - pts[(i - 1 + n) % n][1];
            long v2x = pts[i][0] - pts[(i + 1) % n][0];
            long v2y = pts[i][1] - pts[(i + 1) % n][1];
            angle[i] = ((v1x * v2y - v1y * v2x) > 0);
        }
        
        long res = 0;
        
        for (int spot = 1; spot < n; spot++){
            
            if (cumfreqClock[spot] <= cumfreqCounter[spot]){
                break;
            }
            
            for (int fake = 1; fake < n; fake++) {
                if (fake == spot){
                    continue;
                }
                long total = 0;
                int sI = spot;
                int fI = fake;
                
                while (sI < n && fI < n){
                    if (angle[sI] != angle[fI]){
                        break;
                    }
                    
                    total += len[sI];
                    if (len[sI] != len[fI]) {
                        sI++;
                        fI++;
                        break;
                    }
                    sI++;
                    fI++;
                }
                total += Math.min(cumfreqClock[sI % n], cumfreqCounter[sI % n]);
                res = Math.max(res, total - cumfreqCounter[spot]);
            }
        }
        
        PrintWriter out = new PrintWriter(new FileWriter("lightsout.out"));
        out.println(res);
        out.close();
        stdin.close();
    }
}