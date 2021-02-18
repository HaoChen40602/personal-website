import java.io.*;
import java.util.*;
public class bphoto{
    public static void main(String[] args) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("README.txt"));
        BufferedReader f = new BufferedReader(new FileReader("bphoto.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
        int N = Integer.parseInt(f.readLine());
        cow[] arr = new cow[N];
        for(int i = 0; i < N; i++){
            arr[i] = new cow(i, Integer.parseInt(f.readLine()));
        }
        Arrays.sort(arr);
        BIT rangeSum = new BIT(N);
        int ans = 0;
        for(int i = N - 1; i >= 0; i--){
            cow current = arr[i];
            int leftNum = rangeSum.query(current.i - 1);
            int rightNum = rangeSum.query(N - 1) - rangeSum.query(current.i);
            if(leftNum > 2 * rightNum || rightNum > 2 * leftNum){
                ans++;
            }
            rangeSum.update(current.i, 1);
        }
        pw.println(ans);
        pw.close();
    }
}

class BIT {
    public int[] tree;
    public BIT(int n) {
        tree = new int[n + 5];
    }
    
    public void update(int index, int val) {
        index++;
        while(index < tree.length) {
            tree[index] += val;
            index += index & -index;
        }
    }
    
    public int query(int index){
        if(index == -1){
            return 0;
        }
        int ret = 0;
        index++;
        while(index > 0) {
            ret += tree[index];
            index -= index & -index;
        }
        return ret;
    }
}

class cow implements Comparable<cow>{
    public int i;
    public int h;
    public cow(int ii, int hh){
        i = ii;
        h = hh;
    }
    
    public int compareTo(cow other){
        return this.h - other.h;
    }
}