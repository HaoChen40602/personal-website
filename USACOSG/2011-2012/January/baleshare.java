import java.util.*;
import java.io.*;

public class baleshare{
    public static void main(String[] args) throws Exception {
        
        Scanner f = new Scanner(System.in);
        boolean[][][] good = new boolean[2][800][800];
        good[0][0][0] = true;
        int n = f.nextInt();
        int bale = 0;
        int tsum = 0;
        for (int i = 0; i < n; i++){
            bale = f.nextInt();
            tsum += bale;
            for (int j = 0; j < 700; j++){
                for (int k = 0; k < 700; k++){
                    if(good[i % 2][j][k]){
                        good[(i + 1) % 2][j][k] = true;
                        good[(i + 1) % 2][j + bale][k] = true;
                        good[(i + 1) % 2][j][k + bale] = true;
                    }
                }
            }
        }
        
        int ans = 700;
        
        for (int i = 0; i < 700; i++){
            for (int j = 0; j < 700; j++){
                if (good[n % 2][i][j]){
                    ans = Math.min(ans, Math.max(i, Math.max(j, tsum - (i + j))));
                }
            }
        }
        System.out.println(ans);
    }
}