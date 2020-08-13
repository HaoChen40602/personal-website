import java.util.*;
import java.io.*;
public class slowdown {
    public static void main(String[] args) throws Exception {
        //Scanner stdin = new Scanner(new File("slowdown.in"));
        Scanner stdin = new Scanner(new File("README.txt"));
        int n = stdin.nextInt();
        ArrayList<Integer> timeList = new ArrayList<Integer>();
        ArrayList<Integer> xList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++){
            char type = stdin.next().charAt(0);
            int val = stdin.nextInt();
            if (type == 'T'){
                timeList.add(val);
            }else{
                xList.add(val);
            }
        }
        
        xList.add(1000);
        timeList.add(1000000000);
        Collections.sort(timeList);
        Collections.sort(xList);
        int stopIdx = xList.indexOf(1000);
        
        int tIdx = 0;
        int xIdx = 0;
        int curDiv = 1;
        int res = -1;
        double curT = 0;
        double curD = 0;
        
        while (xIdx <= stopIdx){
            double deltaT = timeList.get(tIdx) - curT;
            double newD = curD + deltaT / curDiv;
            if (newD < xList.get(xIdx) - 1e-9) {
                curD = newD;
                curT = timeList.get(tIdx);
                tIdx++;
                curDiv++;
            }else if (xList.get(xIdx) < newD - 1e-9) {
                curT = curT + (xList.get(xIdx) - curD) * curDiv;
                curD = xList.get(xIdx);
                xIdx++;
                curDiv++;
            }else{
                curD = newD;
                curT = timeList.get(tIdx);
                tIdx++;
                xIdx++;
                curDiv += 2;
            }
        }
        res = (int)(curT + 0.5);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));
        out.println(res);
        out.close();
        stdin.close();
    }
}